/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import distsys.smart_recruitment.auth.JwtUtil;
import distsys.smart_recruitment.auth.Constants;
import io.grpc.Metadata;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import generated.grpc.candidateengagementservice.CandidateEngagementServiceGrpc;
import generated.grpc.candidateengagementservice.SlotSelection;
import generated.grpc.candidateengagementservice.SlotDeliveryConfirmation;
import generated.grpc.candidateengagementservice.CandidateSlotChoice;
import generated.grpc.candidateengagementservice.SchedulingConfirmation;

public class CandidateEngagementClient {
    private static final Logger logger = Logger.getLogger(CandidateEngagementClient.class.getName());

    // JWT token key for authorization - should match the key used in the server interceptor
    private static final Metadata.Key<String> JWT_METADATA_KEY = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    // Default client ID for development
    private static final String DEFAULT_CLIENT_ID = "client123";

    private final ManagedChannel channel;
    private final CandidateEngagementServiceGrpc.CandidateEngagementServiceBlockingStub blockingStub;
    private final CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub;
    private final String clientId;

    // Constructor for the client
    public CandidateEngagementClient(String host, int port) {
        this(host, port, DEFAULT_CLIENT_ID);
    }

    // Constructor for the client with custom client ID
    public CandidateEngagementClient(String host, int port, String clientId) {
        this.clientId = clientId;
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // For development only, not secure for production
                .build();

        // Generate JWT token using JwtUtil
        String jwtToken = "Bearer " + JwtUtil.generateToken(clientId);

        // Create the auth metadata using the JWT token
        Metadata metadata = new Metadata();
        metadata.put(JWT_METADATA_KEY, jwtToken);

        // Create stubs with attached auth metadata
        this.blockingStub = MetadataUtils.attachHeaders(
                CandidateEngagementServiceGrpc.newBlockingStub(channel),
                metadata);

        this.asyncStub = MetadataUtils.attachHeaders(
                CandidateEngagementServiceGrpc.newStub(channel),
                metadata);

        logger.info("Client initialized with JWT token for client ID: " + clientId);
    }

    // Shutdown the channel
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // Method to send interview slots to a candidate
    public void sendInterviewSlots(String candidateName, String[] times, String[] locations) {
        logger.info("Sending interview slots for candidate: " + candidateName);

        final CountDownLatch finishLatch = new CountDownLatch(1);

        // Create a response observer
        StreamObserver<SlotDeliveryConfirmation> responseObserver = new StreamObserver<SlotDeliveryConfirmation>() {
            @Override
            public void onNext(SlotDeliveryConfirmation confirmation) {
                logger.info("Received confirmation: slots delivered = " + confirmation.getSlotsDelivered() +
                        ", delivery time = " + confirmation.getDeliveryTime());
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.WARNING, "Send interview slots failed: {0}", t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Completed sending slots");
                finishLatch.countDown();
            }
        };

        // Get the request observer
        StreamObserver<SlotSelection> requestObserver = asyncStub.sendInterviewSlots(responseObserver);

        try {
            // Send each slot
            for (int i = 0; i < times.length && i < locations.length; i++) {
                SlotSelection slot = SlotSelection.newBuilder()
                        .setCandidateName(candidateName)
                        .setSlotTime(times[i])
                        .setSlotLocation(locations[i])
                        .build();

                requestObserver.onNext(slot);
                logger.info("Sent slot: time = " + times[i] + ", location = " + locations[i]);

                // Simulate some processing time
                Thread.sleep(100);
            }

            // Mark the end of requests
            requestObserver.onCompleted();

            // Wait for the server to respond
            if (!finishLatch.await(1, TimeUnit.MINUTES)) {
                logger.warning("sendInterviewSlots timed out");
            }

        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Send interview slots interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    // Method to receive a candidate's slot choice
    public void receiveCandidateSlotChoice(String candidateName, String chosenTime, String chosenLocation) {
        logger.info("Processing candidate slot choice for candidate: " + candidateName);

        CandidateSlotChoice choice = CandidateSlotChoice.newBuilder()
                .setCandidateName(candidateName)
                .setChosenTime(chosenTime)
                .setChosenLocation(chosenLocation)
                .build();

        try {
            SchedulingConfirmation confirmation = blockingStub.receiveCandidateSlotChoice(choice);
            logger.info("Received confirmation: confirmed = " + confirmation.getConfirmed() +
                    ", confirmation time = " + confirmation.getConfirmationTime());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }

    /**
     * Method to receive a candidate's slot choice and return the confirmation response
     * This is used by the GUI to display confirmation details
     *
     * @param candidateName The name of the candidate
     * @param chosenTime The time chosen by the candidate
     * @param chosenLocation The location chosen by the candidate
     * @return The SchedulingConfirmation from the server
     * @throws StatusRuntimeException if the RPC call fails
     */
    public SchedulingConfirmation receiveCandidateSlotChoiceWithResponse(String candidateName, String chosenTime, String chosenLocation) {
        logger.info("Processing candidate slot choice for candidate: " + candidateName);

        CandidateSlotChoice choice = CandidateSlotChoice.newBuilder()
                .setCandidateName(candidateName)
                .setChosenTime(chosenTime)
                .setChosenLocation(chosenLocation)
                .build();

        try {
            SchedulingConfirmation confirmation = blockingStub.receiveCandidateSlotChoice(choice);
            logger.info("Received confirmation: confirmed = " + confirmation.getConfirmed() +
                    ", confirmation time = " + confirmation.getConfirmationTime());
            return confirmation;
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            throw e; // Rethrow the exception for the GUI to handle
        }
    }

    // Main method with an interactive example
    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = 50051;

        // Allow command-line args to override the defaults
        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        // Create a client
        CandidateEngagementClient client = new CandidateEngagementClient(host, port);

        try {
            // Interactive mode for testing
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nCandidate Engagement Client");
                System.out.println("1. Send Interview Slots");
                System.out.println("2. Process Candidate Slot Choice");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1-3): ");

                int choice = 0;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        // Send interview slots
                        System.out.print("Enter candidate name: ");
                        String candidateName = scanner.nextLine();

                        System.out.print("How many slots do you want to send? ");
                        int numSlots = 0;
                        try {
                            numSlots = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                            continue;
                        }

                        String[] times = new String[numSlots];
                        String[] locations = new String[numSlots];

                        for (int i = 0; i < numSlots; i++) {
                            System.out.print("Enter time for slot " + (i+1) + " (e.g., 2025-04-21 10:00): ");
                            times[i] = scanner.nextLine();

                            System.out.print("Enter location for slot " + (i+1) + " (e.g., Room A): ");
                            locations[i] = scanner.nextLine();
                        }

                        client.sendInterviewSlots(candidateName, times, locations);
                        break;

                    case 2:
                        // Process candidate slot choice
                        System.out.print("Enter candidate name: ");
                        String candName = scanner.nextLine();

                        System.out.print("Enter chosen time: ");
                        String chosenTime = scanner.nextLine();

                        System.out.print("Enter chosen location: ");
                        String chosenLocation = scanner.nextLine();

                        client.receiveCandidateSlotChoice(candName, chosenTime, chosenLocation);
                        break;

                    case 3:
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } finally {
            // Make sure to shutdown the client
            client.shutdown();
        }
    }
}