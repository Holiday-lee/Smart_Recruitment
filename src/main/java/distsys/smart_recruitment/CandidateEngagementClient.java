/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import distsys.smart_recruitment.auth.BearerToken;
import distsys.smart_recruitment.auth.JwtUtil;
import generated.grpc.candidateengagementservice.ApplicationStatus;
import generated.grpc.candidateengagementservice.CandidateEngagementServiceGrpc;
import generated.grpc.candidateengagementservice.NotificationStatus;
import generated.grpc.candidateengagementservice.SchedulingConfirmation;
import generated.grpc.candidateengagementservice.SlotSelection;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
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
            // Generate JWT token for authentication
            String jwt = JwtUtil.generateToken("CandidateEngagementClient");
            logger.info("Generated JWT token for authentication");

            // Create authentication credentials with the token
            BearerToken token = new BearerToken(jwt);

            // Create a blocking stub for Unary with authentication
            CandidateEngagementServiceGrpc.CandidateEngagementServiceBlockingStub blockingStub =
                    CandidateEngagementServiceGrpc.newBlockingStub(channel)
                    .withCallCredentials(token);

            // Create a stub for Client Streaming with authentication
            CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub =
                    CandidateEngagementServiceGrpc.newStub(channel)
                    .withCallCredentials(token);

            // Call SendStatusUpdate method
            sendStatusUpdate(blockingStub);

            // Call ConfirmInterviewSlot method
            confirmInterviewSlot(asyncStub);

            // Wait for a bit to receive responses
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Client interrupted: " + e.getMessage(), e);
        } finally {
            // Shut down the channel after the calls are complete
            logger.info("Shutting down channel...");
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            logger.info("Client shutdown complete.");
        }
    }

    // Method : SendStatusUpdate (Unary call)
    private static void sendStatusUpdate(CandidateEngagementServiceGrpc.CandidateEngagementServiceBlockingStub blockingStub) {
        // Build the request with empty values
        ApplicationStatus statusRequest = ApplicationStatus.newBuilder()
                .setCandidateId("")
                .setStatus("")
                .setMessage("")
                .build();

        logger.info("Sending status update request...");

        try {
            // Call the SendStatusUpdate method
            NotificationStatus response = blockingStub.sendStatusUpdate(statusRequest);

            // Log the response
            logger.info("Status Update sent. Delivered: " + response.getDelivered());
            logger.info("Send Time: " + response.getSendtime());
        } catch (StatusRuntimeException e) {
            // Log the error and handle gRPC status codes
            logger.log(Level.SEVERE, "gRPC error calling sendStatusUpdate: " + e.getStatus().getDescription(), e);
        } catch (Exception e) {
            // Log any other errors
            logger.log(Level.SEVERE, "Error calling sendStatusUpdate: " + e.getMessage(), e);
        }
    }

    // Method: ConfirmInterviewSlot (Client Streaming)
    private static void confirmInterviewSlot(CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub) {
        final CountDownLatch finishLatch = new CountDownLatch(1);

        // Create an observer to send the SlotSelections
        StreamObserver<SlotSelection> requestObserver = asyncStub.confirmInterviewSlot(new StreamObserver<SchedulingConfirmation>() {
            @Override
            public void onNext(SchedulingConfirmation schedulingConfirmation) {
                // Handle the confirmation received from the server
                logger.info("Received confirmation: " + schedulingConfirmation.getConfirmed());
            }

            @Override
            public void onError(Throwable t) {
                // Handle any errors
                logger.log(Level.SEVERE, "Error during stream: " + t.getMessage(), t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                // Handle the completion of the stream
                logger.info("Completed receiving interview slots.");
            }
        });

        try {
            logger.info("Sending slot selection request...");

            // Send SlotSelection to the server with empty values
            SlotSelection slot = SlotSelection.newBuilder()
                    .setCandidateId("")
                    .setSelectedTime("")
                    .setSelectedLocation("")
                    .build();

            // Send slot
            requestObserver.onNext(slot);


            // Simulate checking for invalid input and cancelling the request if needed
            if (slot.getCandidateId().isEmpty() || slot.getSelectedTime().isEmpty() || slot.getSelectedLocation().isEmpty()) {
                String errorMessage = "Invalid slot selection: Candidate ID, time, or location is empty. Cancelling request.";
                logger.warning(errorMessage);
                requestObserver.onError(new IllegalArgumentException(errorMessage));
                return;  // Stop further processing if input is invalid
            }

            // Complete the request stream (no more slots will be sent)
            requestObserver.onCompleted();

            // Wait for the server to complete the stream and respond
            if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.severe("confirmInterviewSlot did not finish within 1 minute");
            }

        } catch (Exception e) {
            // Log errors in sending the stream and cancel the request
            logger.log(Level.SEVERE, "Error in confirmInterviewSlot: " + e.getMessage(), e);
            requestObserver.onError(e);
        }
    }
}