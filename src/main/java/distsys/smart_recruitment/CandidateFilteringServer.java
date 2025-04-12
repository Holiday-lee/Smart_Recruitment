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
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Combined server and service implementation for CandidateFilteringService
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
        // Create and start the gRPC server with our service implementation
        server = ServerBuilder.forPort(port)
                .addService(new CandidateFilteringServiceImpl())
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

    /**
     * Implementation of the CandidateFilteringService.
     * This inner class implements both service methods:
     * 1. scoringCandidateResume - Unary call to score a single resume
     * 2. qualifiedCandidateList - Server streaming to get qualified candidates
     */
    private static class CandidateFilteringServiceImpl extends CandidateFilteringServiceGrpc.CandidateFilteringServiceImplBase {

        public void scoringCandidateResume(CandidateResume request,
            StreamObserver<ResumeScore> responseObserver) {

            System.out.println("Received request to score resume for candidate: " + request.getCandidateName());

            // Calculate score based on the resume
            double score = calculateResumeScore(request);

            // Create response
            ResumeScore response = ResumeScore.newBuilder()
                    .setCandidateId(request.getCandidateId())
                    .setScore(score)
                    .build();

            // Send response
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        public void qualifiedCandidateList(QualificationCriteria request,
            StreamObserver<QualifiedCandidate> responseObserver) {

            System.out.println("Received request for qualified candidates with minimum score: " + request.getMinScore());

            // Get list of candidates (in a real app, this would come from a database)
            List<CandidateResume> candidateList = getDummyCandidates();

            // Filter candidates based on criteria
            for (CandidateResume candidate : candidateList) {
                double score = calculateResumeScore(candidate);

                // If candidate meets the criteria
                if (score >= request.getMinScore()) {
                    // Create a qualified candidate response
                    QualifiedCandidate qualifiedCandidate = QualifiedCandidate.newBuilder()
                            .setCandidateId(candidate.getCandidateId())
                            .setCandidateName(candidate.getCandidateName())
                            .setScore(score)
                            .build();

                    // Stream the candidate to the client
                    responseObserver.onNext(qualifiedCandidate);
                }
            }

            // Signal the end of the stream
            responseObserver.onCompleted();
        }

        /**
         * Calculate a score for a resume based on skills, experience and resume content
         */
        private double calculateResumeScore(CandidateResume resume) {
            // This is a simple scoring algorithm - in a real system you'd have something more sophisticated

            // Base score - everyone starts with 50 points
            double score = 50.0;

            // Add 5 points for each year of experience (up to 25 points)
            score += Math.min(resume.getYearsExperience() * 5, 25);

            // Add 5 points for each skill (up to 20 points)
            score += Math.min(resume.getSkillsCount() * 5, 20);

            // Add up to 5 points based on resume text length (crude measure of detail)
            int textLength = resume.getResumeText().length();
            score += Math.min(textLength / 200, 5); // 1 point per 200 chars up to 5 points

            return score;
        }

        /**
         * Get a list of dummy candidates for testing
         * In a real application, this would come from a database
         */
        private List<CandidateResume> getDummyCandidates() {
            List<CandidateResume> candidates = new ArrayList<>();

            // Candidate 1 - Java Developer
            CandidateResume candidate1 = CandidateResume.newBuilder()
                    .setCandidateId("C001")
                    .setCandidateName("Alex Johnson")
                    .setResumeText("Experienced Java developer with 5 years of experience in enterprise applications. "
                            + "Strong knowledge of Spring, Hibernate, and microservices architecture.")
                    .addSkills("Java")
                    .addSkills("Spring")
                    .addSkills("Hibernate")
                    .addSkills("Microservices")
                    .setYearsExperience(5)
                    .build();
            candidates.add(candidate1);

            // Candidate 2 - Frontend Developer
            CandidateResume candidate2 = CandidateResume.newBuilder()
                    .setCandidateId("C002")
                    .setCandidateName("Sam Wilson")
                    .setResumeText("Frontend developer with 3 years of experience in React and JavaScript.")
                    .addSkills("React")
                    .addSkills("JavaScript")
                    .addSkills("HTML")
                    .addSkills("CSS")
                    .setYearsExperience(3)
                    .build();
            candidates.add(candidate2);

            // Candidate 3 - DevOps Engineer
            CandidateResume candidate3 = CandidateResume.newBuilder()
                    .setCandidateId("C003")
                    .setCandidateName("Taylor Smith")
                    .setResumeText("DevOps engineer with 7 years of experience in AWS, Docker, and Kubernetes.")
                    .addSkills("AWS")
                    .addSkills("Docker")
                    .addSkills("Kubernetes")
                    .addSkills("Jenkins")
                    .addSkills("Terraform")
                    .setYearsExperience(7)
                    .build();
            candidates.add(candidate3);

            // Candidate 4 - Junior Developer
            CandidateResume candidate4 = CandidateResume.newBuilder()
                    .setCandidateId("C004")
                    .setCandidateName("Jordan Lee")
                    .setResumeText("Junior Java developer with 1 year of experience.")
                    .addSkills("Java")
                    .addSkills("SQL")
                    .setYearsExperience(1)
                    .build();
            candidates.add(candidate4);

            return candidates;
        }
    }
}