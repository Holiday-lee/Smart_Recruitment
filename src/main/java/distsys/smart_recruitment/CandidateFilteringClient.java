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
 * UNARY METHOD TYPE / INPUT: A candicate's resume / OUTPUT: A score for the resume
 * rpc ScoringCandidateResume(CandidateResume) returns (ResumeScore)
 *
 * SERVER-STREAMING METHOD TYPE / INPUT: Criteria filter qualified candidates / OUTPUT: A stream of qualified candidates
 * rpc QualifiedCandidateList(QualificationCriteria) returns (stream QualifiedCandidate)
 */
public class CandidateFilteringClient {
    private static final Logger logger = Logger.getLogger(CandidateFilteringClient.class.getName());

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 50051;

        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            // Create a blocking stub for both methods
            CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub =
                    CandidateFilteringServiceGrpc.newBlockingStub(channel);

            // Call ScoringCandidateResume method
            scoreCandidate(blockingStub);

            // Call QualifiedCandidateList method
            getQualifiedCandidates(blockingStub);

            // Wait for a bit to receive responses
            Thread.sleep(3000);

        } finally {
            // Shutdown the channel after the calls are complete
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Client shutdown complete.");
        }
    }

    /**
     * UNARY METHOD TYPE / INPUT: A candicate's resume / OUTPUT: A score for the resume
     * rpc ScoringCandidateResume(CandidateResume) returns (ResumeScore)
     */
    private static void scoreCandidate(CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub) {
        // Build resume request with empty values
        CandidateResume scoreRequest = CandidateResume.newBuilder()
                .setCandidateId("test-id")
                .setCandidateName("Test Candidate")
                .setResumeText("This is a test resume text.")
                .addSkills("Java")  // Add at least one skill
                .setYearsExperience(3)  // Set some experience
                .build();

        System.out.println("Sending candidate resume request...");

        try {
            // Call the scoringCandidateResume method
            ResumeScore response = blockingStub.scoringCandidateResume(scoreRequest);

            // Log the response
            System.out.println("Resume score received: " + response.getScore() + " for candidate ID: " + response.getCandidateId());
        } catch (Exception e) {
            System.err.println("Error calling scoringCandidateResume: " + e.getMessage());
        }
    }

    /**
     * SERVER-STREAMING METHOD TYPE / INPUT: Criteria filter qualified candidates / OUTPUT: A stream of qualified candidates
     * rpc QualifiedCandidateList(QualificationCriteria) returns (stream QualifiedCandidate)
     */
    private static void getQualifiedCandidates(CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub) {
        System.out.println("\nRequesting qualified candidates...");

        // Build qualification criteria
        QualificationCriteria request = QualificationCriteria.newBuilder()
                .setMinScore(70.0)  // Set some minimum score
                .build();

        try {
            // Call the streaming service method
            Iterator<QualifiedCandidate> candidatesIterator = blockingStub.qualifiedCandidateList(request);

            System.out.println("Qualified candidates with minimum score 70.0:");

            // Process each response as it arrives
            int count = 0;
            while (candidatesIterator.hasNext()) {
                QualifiedCandidate candidate = candidatesIterator.next();
                count++;
                System.out.println("  " + count + ". " + candidate.getCandidateName() +
                        " (ID: " + candidate.getCandidateId() + ") with score: " + candidate.getScore());
            }

            if (count == 0) {
                System.out.println("  No qualified candidates found.");
            }

        } catch (StatusRuntimeException e) {
            System.err.println("Error calling qualifiedCandidateList: " + e.getMessage());
        }
    }
}