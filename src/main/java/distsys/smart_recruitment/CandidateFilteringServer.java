/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import distsys.smart_recruitment.auth.AuthorizationServerInterceptor;
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
import java.util.logging.Logger;
import distsys.smart_recruitment.auth.Constants;


public class CandidateFilteringServer extends CandidateFilteringServiceGrpc.CandidateFilteringServiceImplBase {
    private static final Logger logger = Logger.getLogger(CandidateFilteringServer.class.getName());

    // store resume list from user input on GUI
    private List<CandidateResume> candidateDatabase = new ArrayList<>();

    public static void main(String[] args) {
        CandidateFilteringServer candidateFilteringServer = new CandidateFilteringServer();

        int port = 50051;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(candidateFilteringServer)
                    .intercept(new AuthorizationServerInterceptor()) // Add JWT authentication interceptor
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            logger.info("Server started, listening on " + port);

            server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * UNARY METHOD TYPE
     * INPUT: A candidate's resume
     * OUTPUT: A score for the resume
     * rpc ScoringCandidateResume(CandidateResume) returns (ResumeScore) {}
     */
    @Override
    public void scoringCandidateResume(CandidateResume request, StreamObserver<ResumeScore> responseObserver) {

        logger.info("Received request to score resume for candidate: " + request.getCandidateName());

        // Calculate score based on the resume
        double score = calculateResumeScore(request);

        // store the candidate in arrayList
        storeCandidateInDatabase(request, score);

        ResumeScore response = ResumeScore.newBuilder()
                .setScore(score)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /*
     * SERVER-STREAMING METHOD TYPE
     * INPUT: Criteria to filter qualified candidates/threshold: resume scores over/equal 80 marks
     * OUTPUT: A stream of qualified candidates
     * rpc QualifiedCandidateList(QualificationCriteria) returns (stream QualifiedCandidate)
     */
    @Override
    public void qualifiedCandidateList(QualificationCriteria request, StreamObserver<QualifiedCandidate> responseObserver) {

        logger.info("Received request for qualified candidates with minimum score: " + request.getMinScore());

        // Retrieve candidates
        List<CandidateResume> candidateList = getCandidatesFromDatabase();

        // Filter candidates based on criteria
        for (CandidateResume candidate : candidateList) {
            double score = calculateResumeScore(candidate);

            // If candidate meets the criteria
            if (score >= request.getMinScore()) {
                QualifiedCandidate qualifiedCandidate = QualifiedCandidate.newBuilder()
                        .setCandidateName(candidate.getCandidateName())
                        .setScore(score)
                        .build();

                // Stream the candidate to the client
                responseObserver.onNext(qualifiedCandidate);
            }
        }

        //end of the stream
        responseObserver.onCompleted();
    }

    /**
     * Calculate a score for a resume based on experience, skills, and resume content
     */
    private double calculateResumeScore(CandidateResume resume) {
        double score = 0;

	// as in Client.java, yearOfExperience has set to 0
	int yearsExp = resume.getYearsExperience();
	if (yearsExp > 0) {
            score += Math.min(yearsExp * 10, 70);
	}

        // Add 5 points for each skill (up to 25 points)
        score += Math.min(resume.getSkillsCount() * 5, 25);

        // Add up to 5 points based on resume text length/other details
        int textLength = resume.getResumeText().length();
        score += Math.min(textLength / 200, 5); // 1 point per 200 chars

        return score;
    }

    // Store a candidate in the database
    private void storeCandidateInDatabase(CandidateResume candidate, double score) {
        // Check if candidate already exists
        for (int i = 0; i < candidateDatabase.size(); i++) {
            if (candidateDatabase.get(i).getCandidateName().equals(candidate.getCandidateName())) {
                // Replace existing candidate
                candidateDatabase.set(i, candidate);
                return;
            }
        }

        // if no exist, add new candidate
        candidateDatabase.add(candidate);
        System.out.println("Candidate " + candidate.getCandidateName() + " added to database with score: " + score);
    }

	// retrieve the candidate from database
    private List<CandidateResume> getCandidatesFromDatabase() {
        // For now, just return the in-memory list
        // In a production environment, this would be replaced with actual database queries
        return candidateDatabase;
    }

    /**
     * Add a new candidate to the database
     * This method can be called from the GUI
     */
    public void addCandidate(CandidateResume candidate) {
        storeCandidateInDatabase(candidate, calculateResumeScore(candidate));
    }

    /**
     * Get all candidates
     * This method can be called from the GUI
     */
    public List<CandidateResume> getAllCandidates() {
        return getCandidatesFromDatabase();
    }
}