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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Server implementation for the Candidate Engagement Service
 */
public class CandidateEngagementServer extends CandidateEngagementServiceGrpc.CandidateEngagementServiceImplBase {
    private static final Logger logger = Logger.getLogger(CandidateEngagementServer.class.getName());

    // In-memory storage for candidate slot choices and confirmations
    // In a production system, this would be replaced with a database
    private final Map<String, List<SlotSelection>> candidateSlots = new ConcurrentHashMap<>();
    private final Map<String, CandidateSlotChoice> candidateChoices = new ConcurrentHashMap<>();
    private final Map<String, SchedulingConfirmation> confirmations = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        CandidateEngagementServer candidateEngagementServer = new CandidateEngagementServer();

        int port = 50053;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(candidateEngagementServer)
                    .intercept(new AuthorizationServerInterceptor()) // Add JWT authentication interceptor
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // CLIENT-STREAMING METHOD TYPE
    // rpc SendInterviewSlots(stream SlotSelection) returns (SlotDeliveryConfirmation) {}
    @Override
    public StreamObserver<SlotSelection> sendInterviewSlots(StreamObserver<SlotDeliveryConfirmation> responseObserver) {
        // Get the authenticated client ID
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        logger.info("Processing interview slots from client: " + clientId);

        return new StreamObserver<SlotSelection>() {

            // Create a list to store all SlotSelection objects from the stream
            List<SlotSelection> slotSelections = new ArrayList<>();
            String candidateName = "";

            @Override
            public void onNext(SlotSelection slotSelection) {
                slotSelections.add(slotSelection);
                candidateName = slotSelection.getCandidateName();
                logger.info("Received SlotSelection for candidate: " + slotSelection.getCandidateName() +
                        " time: " + slotSelection.getSlotTime() +
                        " Location: " + slotSelection.getSlotLocation());
            }

            @Override
            public void onError(Throwable t) {
                logger.info("Error during stream processing: " + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            // once done collecting stream slotSelection
            public void onCompleted() {
                // Generate timestamp and message ID
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = sdf.format(new Date());

                // Store the slots for this candidate
                if (!candidateName.isEmpty() && !slotSelections.isEmpty()) {
                    candidateSlots.put(candidateName, new ArrayList<>(slotSelections));
                    logger.info("Stored " + slotSelections.size() + " slots for candidate: " + candidateName);
                }

                // Create the SlotDeliveryConfirmation object based on the collected data
                SlotDeliveryConfirmation confirmation = SlotDeliveryConfirmation.newBuilder()
                        .setSlotsDelivered(true)
                        .setDeliveryTime(timestamp)
                        .build();

                logger.info("Sending slots delivery confirmation for candidate: " +
                        (candidateName.isEmpty() ? "unknown" : candidateName) +
                        ", total slots: " + slotSelections.size());

                // Send back the response to client
                responseObserver.onNext(confirmation);
                responseObserver.onCompleted();
            }
        };
    }

    // UNARY METHOD TYPE
    // rpc ReceiveCandidateSlotChoice(CandidateSlotChoice) returns (SchedulingConfirmation) {}
    @Override
    public void receiveCandidateSlotChoice(CandidateSlotChoice request, StreamObserver<SchedulingConfirmation> responseObserver) {
        // Get the authenticated client ID
        String clientId = Constants.CLIENT_ID_CONTEXT_KEY.get();
        logger.info("Processing slot selection from client: " + clientId);

        String candidateName = request.getCandidateName();
        String chosenTime = request.getChosenTime();
        String chosenLocation = request.getChosenLocation();

        logger.info("Received slot selection from candidate " + candidateName +
                ": time = " + chosenTime +
                ", location = " + chosenLocation);

        // Store the candidate's choice
        candidateChoices.put(candidateName, request);

        // Validate the choice against available slots (in a real system)
        boolean isValidChoice = validateCandidateChoice(candidateName, chosenTime, chosenLocation);

        // Generate timestamp for confirmation
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());

        // Create scheduling confirmation response
        SchedulingConfirmation schedulingConfirmation = SchedulingConfirmation.newBuilder()
                .setConfirmed(isValidChoice)
                .setConfirmationTime(timestamp)
                .build();

        // Store the confirmation
        confirmations.put(candidateName, schedulingConfirmation);

        // Log the confirmation status
        if (isValidChoice) {
            logger.info("Confirmed slot choice for candidate: " + candidateName);
        } else {
            logger.warning("Invalid slot choice for candidate: " + candidateName);
        }

        // send back the response to client
        responseObserver.onNext(schedulingConfirmation);
        responseObserver.onCompleted();
    }

    /**
     * Validate that the candidate's choice matches one of the offered slots
     *
     * @param candidateName The name of the candidate
     * @param chosenTime The time chosen by the candidate
     * @param chosenLocation The location chosen by the candidate
     * @return true if the choice is valid, false otherwise
     */
    private boolean validateCandidateChoice(String candidateName, String chosenTime, String chosenLocation) {
        // In a real system, you would validate against the actual slots offered to this candidate
        // For this demo, we'll just assume all choices are valid if we have slots for this candidate

        // Simple validation: check if we have slots for this candidate
        if (!candidateSlots.containsKey(candidateName)) {
            logger.warning("No slots found for candidate: " + candidateName);
            return true; // For demo purposes, we'll still confirm even without validation
        }

        // More detailed validation: check if the chosen time and location match any offered slot
        List<SlotSelection> availableSlots = candidateSlots.get(candidateName);
        for (SlotSelection slot : availableSlots) {
            if (slot.getSlotTime().equals(chosenTime) && slot.getSlotLocation().equals(chosenLocation)) {
                return true;
            }
        }

        // For demo purposes, we'll still confirm even if validation fails
        // In a real system, you might reject invalid choices
        logger.warning("Candidate choice doesn't match any offered slot but confirming anyway for demo");
        return true;
    }

    /**
     * Get all slots offered to a candidate
     * This would be used by other parts of your system to check what slots were offered
     *
     * @param candidateName The name of the candidate
     * @return List of slots offered to the candidate, or empty list if none
     */
    public List<SlotSelection> getCandidateSlots(String candidateName) {
        return candidateSlots.getOrDefault(candidateName, new ArrayList<>());
    }

    /**
     * Get a candidate's chosen slot
     * This would be used by other parts of your system to check what the candidate chose
     *
     * @param candidateName The name of the candidate
     * @return The candidate's choice, or null if not found
     */
    public CandidateSlotChoice getCandidateChoice(String candidateName) {
        return candidateChoices.get(candidateName);
    }

    /**
     * Get the confirmation status for a candidate
     * This would be used by other parts of your system to check if a slot was confirmed
     *
     * @param candidateName The name of the candidate
     * @return The confirmation, or null if not found
     */
    public SchedulingConfirmation getConfirmationStatus(String candidateName) {
        return confirmations.get(candidateName);
    }
}