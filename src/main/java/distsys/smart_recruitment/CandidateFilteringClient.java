/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import generated.grpc.candidatefilteringservice.CandidateFilteringServiceGrpc;
import generated.grpc.candidatefilteringservice.CandidateResume;
import generated.grpc.candidatefilteringservice.QualificationCriteria;
import generated.grpc.candidatefilteringservice.QualifiedCandidate;
import generated.grpc.candidatefilteringservice.ResumeScore;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client for accessing the CandidateFilteringService
 */
public class CandidateFilteringClient {
    private static final Logger logger = Logger.getLogger(CandidateFilteringClient.class.getName());
    
    // Blocking stub (synchronous calls)
    private final CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub;
    
    private final ManagedChannel channel;
    
    /**
     * Constructor to setup channel and stub
     */
    public CandidateFilteringClient(String host, int port) {
        // Create a communication channel to the server
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Disable TLS (not secure, but simpler for testing)
                .build();
        
        // Create a blocking stub (synchronous calls)
        blockingStub = CandidateFilteringServiceGrpc.newBlockingStub(channel);
    }
    
    /**
     * Shutdown the client
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    
    /**
     * Score a single candidate resume
     */
    public double scoreCandidate(String candidateId, String candidateName, String resumeText,
                              List<String> skills, int yearsExperience) {
        logger.info("Requesting score for candidate: " + candidateName);
        
        // Build resume request
        CandidateResume request = CandidateResume.newBuilder()
                .setCandidateId(candidateId)
                .setCandidateName(candidateName)
                .setResumeText(resumeText)
                .addAllSkills(skills)
                .setYearsExperience(yearsExperience)
                .build();
        
        ResumeScore response;
        try {
            // Call the service method
            response = blockingStub.scoringCandidateResume(request);
            logger.info("Resume score for " + candidateName + ": " + response.getScore());
            return response.getScore();
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return -1; // Indicate error
        }
    }
    
    /**
     * Get list of qualified candidates
     */
    public List<QualifiedCandidate> getQualifiedCandidates(double minScore) {
        logger.info("Requesting qualified candidates with minimum score: " + minScore);
        
        // Build qualification criteria
        QualificationCriteria request = QualificationCriteria.newBuilder()
                .setMinScore(minScore)
                .build();
        
        List<QualifiedCandidate> qualifiedCandidates = new ArrayList<>();
        try {
            // Call the streaming service method
            Iterator<QualifiedCandidate> candidatesIterator = blockingStub.qualifiedCandidateList(request);
            
            // Process each response as it arrives
            while (candidatesIterator.hasNext()) {
                QualifiedCandidate candidate = candidatesIterator.next();
                qualifiedCandidates.add(candidate);
                logger.info("Received qualified candidate: " + candidate.getCandidateName() + 
                           " (ID: " + candidate.getCandidateId() + ") with score: " + candidate.getScore());
            }
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        
        return qualifiedCandidates;
    }
    
    /**
     * Main method to demonstrate client usage
     */
    public static void main(String[] args) throws InterruptedException {
        CandidateFilteringClient client = null;
        try {
            // Connect to the server (localhost:50051 by default)
            client = new CandidateFilteringClient("localhost", 50051);
            
            System.out.println("\n=== Testing ScoringCandidateResume ===");
            // Test 1: Score a new candidate resume
            double score = client.scoreCandidate(
                "C005",
                "Pat Miller",
                "Full stack developer with 4 years of experience in React and Node.js. " +
                "Led a team of 3 developers in building an e-commerce platform.",
                Arrays.asList("React", "Node.js", "JavaScript", "MongoDB"),
                4
            );
            System.out.println("Score received: " + score);
            
            System.out.println("\n=== Testing QualifiedCandidateList ===");
            // Test 2: Get qualified candidates with minimum score of 75
            System.out.println("Qualified Candidates (minimum score: 75):");
            List<QualifiedCandidate> qualifiedCandidates = client.getQualifiedCandidates(75.0);
            System.out.println("Total qualified candidates: " + qualifiedCandidates.size());
            
            // Display summary of qualified candidates
            if (!qualifiedCandidates.isEmpty()) {
                System.out.println("\nSummary of Qualified Candidates:");
                for (QualifiedCandidate candidate : qualifiedCandidates) {
                    System.out.printf("- %s (ID: %s): %.1f points%n", 
                            candidate.getCandidateName(),
                            candidate.getCandidateId(),
                            candidate.getScore());
                }
            }
        } finally {
            // Shutdown the client
            if (client != null) {
                client.shutdown();
            }
        }
    }
}