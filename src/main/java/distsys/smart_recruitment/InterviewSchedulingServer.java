/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSlot;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class InterviewSchedulingServer extends InterviewSchedulingServiceGrpc.InterviewSchedulingServiceImplBase {
    private static final Logger logger = Logger.getLogger(InterviewSchedulingServer.class.getName());
    private Server server;
    private final int port;

    // Constructor with configurable port
    public InterviewSchedulingServer(int port) {
        this.port = port;
    }

    // Start the server
    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(this)  // This is where the base class is extended
                .build()
                .start();
        logger.info("Server started, listening on port " + port);

        // Add shutdown hook to ensure clean shutdown
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Shutting down gRPC server due to JVM shutdown");
                try {
                    InterviewSchedulingServer.this.stop();
                } catch (InterruptedException e) {
                    logger.severe("Error shutting down server: " + e.getMessage());
                }
                logger.info("Server shut down");
            }
        });
    }

    // Stop the server
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    // Block until server is terminated
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // Override the method in the base class to implement the business logic for ArrangeInterviewSlot
    @Override
    public void arrangeInterviewSlot(StreamObserver<CandidateName> requestObserver, StreamObserver<InterviewSlot> responseObserver) {
        System.out.println("Server received candidate names for interview scheduling.");

        // Process the candidate names and arrange interview slots
        try {
            // This will listen to the incoming stream of candidate names
            CandidateName candidate;
            while ((candidate = requestObserver.next()) != null) {
                // Generate an interview slot for each candidate
                InterviewSlot slot = generateInterviewSlot(candidate);
                responseObserver.onNext(slot);
                System.out.println("Arranged interview slot for: " + candidate.getCandidateName());
            }
        } catch (Exception e) {
            responseObserver.onError(e);
        } finally {
            // Complete the response stream
            responseObserver.onCompleted();
        }
    }

    // Simulate interview slot generation
    private InterviewSlot generateInterviewSlot(CandidateName candidate) {
        String time = "10:00 AM"; // Simple static time for illustration
        String location = "Room 101, Tech Building"; // Static location for now

        // Normally, you would generate slots based on availability, etc.
        return InterviewSlot.newBuilder()
                .setTime(time)
                .setLocation(location)
                .build();
    }

    // Main entry point
    public static void main(String[] args) {
        int port = 50051;
        final InterviewSchedulingServer server = new InterviewSchedulingServer(port);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (IOException | InterruptedException e) {
            logger.severe("Server failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}