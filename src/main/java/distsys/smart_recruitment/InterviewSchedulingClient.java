/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSlot;

public class InterviewSchedulingClient {

    public static void main(String[] args) {
        // Set up the channel and stub
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        InterviewSchedulingServiceGrpc.InterviewSchedulingServiceStub asyncStub = InterviewSchedulingServiceGrpc.newStub(channel);

        // Create a stream observer to handle the response stream
        StreamObserver<InterviewSlot> responseObserver = new StreamObserver<InterviewSlot>() {
            @Override
            public void onNext(InterviewSlot value) {
                // Print the received interview slot
                System.out.println("Received slot: " + value.getTime() + " at " + value.getLocation());
            }

            @Override
            public void onError(Throwable t) {
                // Handle error
                System.err.println("Error receiving interview slots: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Handle the completion of the stream
                System.out.println("Completed receiving interview slots.");
            }
        };

        // Create the stream observer for sending candidate names
        StreamObserver<CandidateName> requestObserver = asyncStub.arrangeInterviewSlot(responseObserver);

        try {
            // Send candidate names in a stream
            requestObserver.onNext(CandidateName.newBuilder().setCandidateId("1").setCandidateName("Alice").build());
            requestObserver.onNext(CandidateName.newBuilder().setCandidateId("2").setCandidateName("Bob").build());
            requestObserver.onNext(CandidateName.newBuilder().setCandidateId("3").setCandidateName("Charlie").build());

            // Indicate the end of the stream
            requestObserver.onCompleted();

        } catch (Exception e) {
            // Handle exception
            System.err.println("Error during request stream: " + e.getMessage());
            requestObserver.onError(e);
        }

        // Keep the client running to receive responses
        try {
            Thread.sleep(5000); // Wait for responses to come in
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown the channel
        channel.shutdownNow();
    }
}
