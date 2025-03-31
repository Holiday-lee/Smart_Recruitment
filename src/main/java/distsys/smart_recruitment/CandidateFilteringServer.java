/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Server that hosts the CandidateFilteringService
 */
public class CandidateFilteringServer {
    private static final Logger logger = Logger.getLogger(CandidateFilteringServer.class.getName());
    
    private Server server;
    private final int port;
    
    /**
     * Constructor with configurable port
     */
    public CandidateFilteringServer(int port) {
        this.port = port;
    }
    
    /**
     * Start the server
     */
    public void start() throws IOException {
        // Create and start the gRPC server
        server = ServerBuilder.forPort(port)
                .addService(new CandidateFilteringServiceImplementation())
                .build()
                .start();
        
        logger.info("Server started, listening on port " + port);
        System.out.println("***** Server started, listening on port " + port);
        
        // Add shutdown hook to ensure clean shutdown
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Shutting down gRPC server due to JVM shutdown");
                try {
                    CandidateFilteringServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                logger.info("Server shut down");
            }
        });
    }
    
    /**
     * Stop the server
     */
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
    
    /**
     * Block until server is terminated
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    
    /**
     * Main entry point
     */
    public static void main(String[] args) {
        // Use default port 50051 unless specified
        int port = 50051;
        
        // Parse command line arguments for custom port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.warning("Invalid port number. Using default port " + port);
            }
        }
        
        // Start the server
        final CandidateFilteringServer server = new CandidateFilteringServer(port);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Server interrupted: " + e.getMessage());
            e.printStackTrace();
        }
    }
}