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
import io.grpc.StatusRuntimeException;
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

    private final ManagedChannel channel;
    private final CandidateEngagementServiceGrpc.CandidateEngagementServiceBlockingStub blockingStub;
    private final CandidateEngagementServiceGrpc.CandidateEngagementServiceStub asyncStub;

    // Constructor for the client
    public CandidateEngagementClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // For development only, not secure for production
                .build();
        this.blockingStub = CandidateEngagementServiceGrpc.newBlockingStub(channel);
        this.asyncStub = CandidateEngagementServiceGrpc.newStub(channel);
    }

    // Shutdown the channel
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // Method to send interview slots to a candidate
    public void sendInterviewSlots(String candidateId, String[] times, String[] locations) {
        logger.info("Sending interview slots for candidate: " + candidateId);

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
                        .setCandidateId(candidateId)
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

    // Method to submit a candidate's slot choice
    public void submitSelectedSlot(String candidateId, String chosenTime, String chosenLocation) {
        logger.info("Submitting selected slot for candidate: " + candidateId);

        CandidateSlotChoice choice = CandidateSlotChoice.newBuilder()
                .setCandidateId(candidateId)
                .setChosenTime(chosenTime)
                .setChosenLocation(chosenLocation)
                .build();

        try {
            SchedulingConfirmation confirmation = blockingStub.submitSelectedSlot(choice);
            logger.info("Received confirmation: confirmed = " + confirmation.getConfirmed() +
                    ", confirmation time = " + confirmation.getConfirmationTime());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
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
                System.out.println("2. Submit Selected Slot");
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
                        System.out.print("Enter candidate ID: ");
                        String candidateId = scanner.nextLine();

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

                        client.sendInterviewSlots(candidateId, times, locations);
                        break;

                    case 2:
                        // Submit selected slot
                        System.out.print("Enter candidate ID: ");
                        String candId = scanner.nextLine();

                        System.out.print("Enter chosen time: ");
                        String chosenTime = scanner.nextLine();

                        System.out.print("Enter chosen location: ");
                        String chosenLocation = scanner.nextLine();

                        client.submitSelectedSlot(candId, chosenTime, chosenLocation);
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