/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

import distsys.smart_recruitment.auth.AuthorizationServerInterceptor;
import distsys.smart_recruitment.auth.Constants;
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
public class CandidateEngagementServer extends CandidateEngagementServiceGrpc.CandidateEngagementServiceImplBase{
    private static final Logger logger = Logger.getLogger(CandidateEngagementServer.class.getName());

    public static void main(String[] args){
        CandidateEngagementServer candidateEngagementServer = new CandidateEngagementServer();

        int port = 50053;

        try{
            Server server = ServerBuilder.forPort(port)
                    .addService(candidateEngagementServer)
                    .intercept(new AuthorizationServerInterceptor()) // Add JWT authentication interceptor
                    .build()
                    .start();
                    logger.info("Server started, listening on " + port);
                    logger.info("Server started, listening on " + port);
                    server.awaitTermination();
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // CLIENT-STREAMING METHOD TYPE
    // rpc ConfirmInterviewSlot(stream SlotSelection) returns (SchedulingConfirmation) {}
    @Override
    public StreamObserver<SlotSelection> confirmInterviewSlot(StreamObserver<SchedulingConfirmation> responseObserver){
        // Get the authenticated client ID
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        logger.info("Processing interview slot confirmation from client: " + clientId);

        return new StreamObserver<SlotSelection>(){

            // Create a list to store all SlotSelection objects from the stream
            List<SlotSelection> slotSelections = new ArrayList<>();

            @Override
            public void onNext(SlotSelection slotSelection){
                slotSelections.add(slotSelection);
                logger.info("Received SlotSelection for candidate: " + slotSelection.getCandidateId() + " time: " + slotSelection.getSelectedTime()+ " Location: " + slotSelection.getSelectedLocation());
            }

            @Override
            public void onError(Throwable t){
                logger.info("Error during stream processing: " + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            // once done collected stream slotSelection
            public void onCompleted(){
                // Create the SchedulingConfirmation object based on the collected data
                SchedulingConfirmation confirmation = SchedulingConfirmation.newBuilder()
                        .setConfirmed(true) // = slot confirmed
                        .build();

                // Send back the response to client
                responseObserver.onNext(confirmation);
                responseObserver.onCompleted();
            }
        };
    }

    // UNARY METHOD TYPE
    // rpc SendStatusUpdate(ApplicationStatus) returns (NotificationStatus) {}
    @Override
    public void sendStatusUpdate(ApplicationStatus request, StreamObserver<NotificationStatus> responseObserver){
        // Get the authenticated client ID
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        logger.info("Processing status update from client: " + clientId);

        String candidateId = request.getCandidateId();
        String status = request.getStatus();
        String message = request.getMessage();

        logger.info("Received status update for candidate " + candidateId + ": " + status + " - " + message);

        // Create notificationStatus response
        NotificationStatus notificationStatus = NotificationStatus.newBuilder()
                .setDelivered(true)
                .setSendtime("")
                .build();

        // send back the response to client
        responseObserver.onNext(notificationStatus);
        responseObserver.onCompleted();
    }
}