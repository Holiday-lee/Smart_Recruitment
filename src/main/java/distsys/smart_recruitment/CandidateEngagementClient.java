/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import generated.grpc.candidateengagementservice.ApplicationStatus;
import generated.grpc.candidateengagementservice.CandidateEngagementServiceGrpc;
import generated.grpc.candidateengagementservice.NotificationStatus;
import generated.grpc.candidateengagementservice.SchedulingConfirmation;
import generated.grpc.candidateengagementservice.SlotSelection;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * CLIENT-STREAMING METHOD TYPE / INPUT: A stream of interview slots / OUTPUT: A confirmation from the candidate
 *   rpc ConfirmInterviewSlot(stream SlotSelection) returns (SchedulingConfirmation) {}
 *
 * UNARY METHOD TYPE / INPUT: Candidate ID and application status / OUTPUT: Notification status
 * rpc SendStatusUpdate(ApplicationStatus) returns (NotificationStatus) 
 * 
 */
public class CandidateEngagementClient {

    private static final Logger logger = Logger.getLogger(CandidateEngagementClient.class.getName());

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 50053;

        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            // Create a blocking stub for SendStatusUpdate (Unary)
            CandidateEngagementServiceGrpc.CandidateEngagementServiceBlockingStub blockingStub =
                    CandidateEngagementServiceGrpc.newBlockingStub(channel);

            // Create a stub for ConfirmInterviewSlot (Client Streaming)
            CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub =
                    CandidateEngagementServiceGrpc.newStub(channel);

            // Call SendStatusUpdate method
            sendStatusUpdate(blockingStub);

            // Call ConfirmInterviewSlot method
            confirmInterviewSlot(asyncStub);

            // Wait for a bit to receive responses
            Thread.sleep(3000);

        } finally {
            // Shutdown the channel after the calls are complete
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Client shutdown complete.");
        }
    }

    // Method to invoke SendStatusUpdate (Unary call)
    private static void sendStatusUpdate(CandidateEngagementServiceGrpc.CandidateEngagementServiceBlockingStub blockingStub) {
        // Build the request with empty values
        ApplicationStatus statusRequest = ApplicationStatus.newBuilder()
                .setCandidateId("")
                .setStatus("")
                .setMessage("")
                .build();

        System.out.println("Sending status update request...");

        try {
            // Call the SendStatusUpdate method
            NotificationStatus response = blockingStub.sendStatusUpdate(statusRequest);

            // Log the response
            System.out.println("Status Update sent. Delivered: " + response.getDelivered());
            System.out.println("Send Time: " + response.getSendtime());
        } catch (Exception e) {
            System.err.println("Error calling sendStatusUpdate: " + e.getMessage());
        }
    }

    // Method to invoke ConfirmInterviewSlot (Client Streaming)
    private static void confirmInterviewSlot(CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub) {
        final CountDownLatch finishLatch = new CountDownLatch(1);

        // Create an observer to send the SlotSelections
        StreamObserver<SlotSelection> requestObserver = asyncStub.confirmInterviewSlot(new StreamObserver<SchedulingConfirmation>() {
            @Override
            public void onNext(SchedulingConfirmation schedulingConfirmation) {
                // Handle the confirmation received from the server
                System.out.println("Received confirmation: " + schedulingConfirmation.getConfirmed());
            }

            @Override
            public void onError(Throwable t) {
                // Handle any errors
                System.err.println("Error during stream: " + t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                // Handle the stream completion
                System.out.println("Interview slot confirmation process completed.");
                finishLatch.countDown();
            }
        });

        try {
            System.out.println("Sending slot selection request...");

            // Send SlotSelection to the server with empty values
            SlotSelection slot = SlotSelection.newBuilder()
                    .setCandidateId("")
                    .setSelectedTime("")
                    .setSelectedLocation("")
                    .build();

            // Send slot
            requestObserver.onNext(slot);

            // Complete the request (which tells the server that no more slots will be sent)
            requestObserver.onCompleted();

            // Wait for server to respond
            if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                System.err.println("confirmInterviewSlot can not finish within 1 minute");
            }

        } catch (Exception e) {
            System.err.println("Error in confirmInterviewSlot: " + e.getMessage());
            requestObserver.onError(e);
        }
    }
}