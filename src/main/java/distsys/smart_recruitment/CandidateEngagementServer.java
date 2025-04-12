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
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author jiaki
 */
public class CandidateEngagementServer extends CandidateEngagementServiceGrpc.CandidateEngagementServiceImplBase {
    private static final Logger logger = Logger.getLogger(CandidateEngagementServer.class.getName());

    public static void main(String[] args){

        CandidateEngagementServer candidateEngagementServer = new CandidateEngagementServer();

        int port = 50051;

        try{
            Server server = ServerBuilder.forPort(port)
                    .addService (candidateEngagementServer)
                    .build()
                    .start();
                    logger.info("Server started, listening on " + port);
                    System.out.println(" Server started, listening on " + port);
                    server.awaitTermination();
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }


   @Override
       public io.grpc.stub.StreamObserver<SlotSelection> confirmInterviewSlot(
               StreamObserver<SchedulingConfirmation> responseObserver) {

           // Create a list to collect all SlotSelection objects from the stream
           List<SlotSelection> slotSelections = new ArrayList<>();

           // Return a StreamObserver to handle incoming SlotSelection stream
           return new StreamObserver<SlotSelection>() {

               @Override
               public void onNext(SlotSelection slotSelection) {
                   // Collect each incoming SlotSelection
                   slotSelections.add(slotSelection);
                   System.out.println("Received SlotSelection for candidate: " + slotSelection.getCandidateId()
                           + " with time: " + slotSelection.getSelectedTime());
               }

               @Override
               public void onError(Throwable t) {
                   // Handle any error that occurs in the stream
                   System.err.println("Error during stream processing: " + t.getMessage());
                   responseObserver.onError(t);
               }

               @Override
               public void onCompleted() {
                   // Process the collected SlotSelections and send a confirmation
                   StringBuilder confirmationMessage = new StringBuilder("Interview Slots Confirmed: \n");

                   for (SlotSelection selection : slotSelections) {
                       // For demonstration, print out the selected slots
                       confirmationMessage.append("Candidate: ").append(selection.getCandidateId())
                               .append(", Time: ").append(selection.getSelectedTime())
                               .append(", Location: ").append(selection.getSelectedLocation())
                               .append("\n");
                   }

                   // Create the SchedulingConfirmation object based on the collected data
                   SchedulingConfirmation confirmation = SchedulingConfirmation.newBuilder()
                           .setConfirmed(true)  // For simplicity, we assume the slots are confirmed
                           .build();

                   // Send the response back to the client
                   responseObserver.onNext(confirmation);
                   responseObserver.onCompleted();
               }
           };
    }


    @Override
    public void sendStatusUpdate(ApplicationStatus request, StreamObserver<NotificationStatus> responseObserver){
        // Process incoming ApplicationStatus request
        String candidateId = request.getCandidateId();
        String status = request.getStatus();
        String message = request.getMessage();

        System.out.println("Received status update for candidate " + candidateId +" : " + status + " - " + message);

        // Create notificationStatus response
        NotificationStatus notificationStatus = NotificationStatus.newBuilder()
                .setDelivered(true)
                .setSendtime("2025-04-12T12:00:00Z")
                .build();

        // send response back to the client
        responseObserver.onNext(notificationStatus);
        responseObserver.onCompleted();
    }

}
