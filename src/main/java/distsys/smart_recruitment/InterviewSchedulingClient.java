/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSlot;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterviewSchedulingClient {
    private static final Logger logger = Logger.getLogger(InterviewSchedulingClient.class.getName());
    private final ManagedChannel channel;
    private final InterviewSchedulingServiceGrpc.InterviewSchedulingServiceStub asyncStub;

    // Constructor to set up the channel and async stub
    public InterviewSchedulingClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        asyncStub = InterviewSchedulingServiceGrpc.newStub(channel); // Async stub for bidirectional streaming
    }

    // Shutdown the client
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    // Method to arrange interview slots for multiple candidates
    public void arrangeInterviews(List<CandidateName> candidates) throws InterruptedException {
        // Prepare a count-down latch to handle the asynchronous nature of bidirectional streaming
        CountDownLatch latch = new CountDownLatch(1);

        // Create an observer for the response stream
        StreamObserver<InterviewSlot> responseObserver = new StreamObserver<InterviewSlot>() {
            @Override
            public void onNext(InterviewSlot slot) {
                System.out.println("Received interview slot: " + slot.getTime() + " at " + slot.getLocation());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.SEVERE, "RPC failed: {0}", t.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Interview scheduling completed.");
                latch.countDown();
            }
        };

        // Create the request stream observer for sending candidate names
        StreamObserver<CandidateName> requestObserver = asyncStub.arrangeInterviewSlot(responseObserver);

        try {
            // Send candidate names to the server
            for (CandidateName candidate : candidates) {
                requestObserver.onNext(candidate);
                System.out.println("Sent candidate: " + candidate.getCandidateName());
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        } finally {
            // Mark the end of the stream
            requestObserver.onCompleted();
        }

        // Block and wait for the response to complete
        latch.await();
    }

    // Main method to demonstrate client usage
    public static void main(String[] args) throws InterruptedException {
        InterviewSchedulingClient client = new InterviewSchedulingClient("localhost", 50051);

        // Create a list of candidate names
        List<CandidateName> candidates = Arrays.asList(
                CandidateName.newBuilder().setCandidateId("C001").setCandidateName("Alice Smith").build(),
                CandidateName.newBuilder().setCandidateId("C002").setCandidateName("Bob Johnson").build(),
                CandidateName.newBuilder().setCandidateId("C003").setCandidateName("Charlie Lee").build()
        );

        try {
            // Call the method to arrange interviews for the candidates
            client.arrangeInterviews(candidates);
        } finally {
            // Shutdown the client
            client.shutdown();
        }
    }
}
