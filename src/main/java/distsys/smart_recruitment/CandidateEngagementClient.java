/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

import generated.grpc.candidateengagementservice.ApplicationStatus;
import generated.grpc.candidateengagementservice.CandidateEngagementServiceGrpc;
import generated.grpc.candidateengagementservice.NotificationStatus;
import generated.grpc.candidateengagementservice.SchedulingConfirmation;
import generated.grpc.candidateengagementservice.SlotSelection;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;

/**
 *
 * @author jiaki
 */
public class CandidateEngagementClient {
    
    private static final Logger logger = Logger.getLogger(CandidateEngagementClient.class.getName());
    
    public static void main(String[] args) throws Exception{
        String host = "localhost";
        int port = 50051;
        
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();
        
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

        // Shutdown the channel after the calls are complete
        channel.shutdownNow();
    }

    // Method to invoke SendStatusUpdate (Unary call)
    private static void sendStatusUpdate(CandidateEngagementServiceGrpc.CandidateEngagementServiceBlockingStub blockingStub) {
        // Build the request
        ApplicationStatus statusRequest = ApplicationStatus.newBuilder()
                .setCandidateId("candidate123")
                .setStatus("In Review")
                .setMessage("Candidate is being considered for the interview.")
                .build();

        // Call the SendStatusUpdate method
        NotificationStatus response = blockingStub.sendStatusUpdate(statusRequest);

        // Log the response
        System.out.println("Status Update sent. Delivered: " + response.getDelivered());
        System.out.println("Send Time: " + response.getSendtime());
    }

    // Method to invoke ConfirmInterviewSlot (Client Streaming)
    private static void confirmInterviewSlot(CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub) {
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
            }

            @Override
            public void onCompleted() {
                // Handle the stream completion
                System.out.println("Interview slot confirmation process completed.");
            }
        });

        // Send SlotSelections to the server
        SlotSelection slot1 = SlotSelection.newBuilder()
                .setCandidateId("candidate123")
                .setSelectedTime("2025-04-12T10:00:00")
                .setSelectedLocation("Room 101")
                .build();

        SlotSelection slot2 = SlotSelection.newBuilder()
                .setCandidateId("candidate123")
                .setSelectedTime("2025-04-12T14:00:00")
                .setSelectedLocation("Room 102")
                .build();

        // Send slots one by one
        requestObserver.onNext(slot1);
        requestObserver.onNext(slot2);

        // Complete the request (which tells the server that no more slots will be sent)
        requestObserver.onCompleted();
    }
}
