/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

import distsys.smart_recruitment.auth.AuthorizationServerInterceptor;
import distsys.smart_recruitment.auth.Constants;
import generated.grpc.candidateengagementservice.CandidateEngagementServiceGrpc;
import generated.grpc.candidateengagementservice.CandidateSlotChoice;
import generated.grpc.candidateengagementservice.SchedulingConfirmation;
import generated.grpc.candidateengagementservice.SlotDeliveryConfirmation;
import generated.grpc.candidateengagementservice.SlotSelection;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
    // rpc SendInterviewSlots(stream SlotSelection) returns (SlotDeliveryConfirmation) {}
    @Override
    public StreamObserver<SlotSelection> sendInterviewSlots(StreamObserver<SlotDeliveryConfirmation> responseObserver){
        // Get the authenticated client ID
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        logger.info("Processing interview slots from client: " + clientId);

        return new StreamObserver<SlotSelection>(){

            // Create a list to store all SlotSelection objects from the stream
            List<SlotSelection> slotSelections = new ArrayList<>();
            String candidateId = "";

            @Override
            public void onNext(SlotSelection slotSelection){
                slotSelections.add(slotSelection);
                candidateId = slotSelection.getCandidateId();
                logger.info("Received SlotSelection for candidate: " + slotSelection.getCandidateId() +
                           " time: " + slotSelection.getSlotTime() +
                           " Location: " + slotSelection.getSlotLocation());
            }

            @Override
            public void onError(Throwable t){
                logger.info("Error during stream processing: " + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            // once done collecting stream slotSelection
            public void onCompleted(){
                // Generate timestamp and message ID
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = sdf.format(new Date());
                String messageId = UUID.randomUUID().toString();

                // Create the SlotDeliveryConfirmation object based on the collected data
                SlotDeliveryConfirmation confirmation = SlotDeliveryConfirmation.newBuilder()
                        .setSlotsDelivered(true)
                        .setDeliveryTime(timestamp)
                        .build();

                logger.info("Sending slots delivery confirmation for candidate: " +
                           (candidateId.isEmpty() ? "unknown" : candidateId) +
                           ", total slots: " + slotSelections.size());

                // Send back the response to client
                responseObserver.onNext(confirmation);
                responseObserver.onCompleted();
            }
        };
    }

    // UNARY METHOD TYPE
    // rpc SubmitSelectedSlot(CandidateSlotChoice) returns (SchedulingConfirmation) {}
    @Override
    public void submitSelectedSlot(CandidateSlotChoice request, StreamObserver<SchedulingConfirmation> responseObserver){
        // Get the authenticated client ID
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        logger.info("Processing slot selection from client: " + clientId);

        String candidateId = request.getCandidateId();
        String chosenTime = request.getChosenTime();
        String chosenLocation = request.getChosenLocation();

        logger.info("Received slot selection from candidate " + candidateId +
                   ": time = " + chosenTime +
                   ", location = " + chosenLocation);

        // Generate timestamp for confirmation
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());

        // Create scheduling confirmation response
        SchedulingConfirmation schedulingConfirmation = SchedulingConfirmation.newBuilder()
                .setConfirmed(true)
                .setConfirmationTime(timestamp)
                .build();

        // send back the response to client
        responseObserver.onNext(schedulingConfirmation);
        responseObserver.onCompleted();
    }
}