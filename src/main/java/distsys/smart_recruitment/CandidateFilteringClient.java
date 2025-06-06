/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import distsys.smart_recruitment.auth.BearerToken;
import distsys.smart_recruitment.auth.JwtUtil;
import generated.grpc.candidatefilteringservice.CandidateFilteringServiceGrpc;
import generated.grpc.candidatefilteringservice.CandidateResume;
import generated.grpc.candidatefilteringservice.QualificationCriteria;
import generated.grpc.candidatefilteringservice.QualifiedCandidate;
import generated.grpc.candidatefilteringservice.ResumeScore;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
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

    public static void main(String[] args) throws InterruptedException {

        // set up for gRPC channel
        String host = "localhost";
        int port = 50051;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        // generate JWT token & authentication credentials
        String jwt = JwtUtil.generateToken("CandidateFilteringClient");
        logger.info("JWT token is generated for authentication");
        BearerToken token = new BearerToken(jwt);

        // blocking stub for Unary + .with authentication
        CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub =
                CandidateFilteringServiceGrpc.newBlockingStub(channel)
                .withCallCredentials(token); // authentication

        // asy stub for server streaming + .with authentication
        CandidateFilteringServiceGrpc.CandidateFilteringServiceStub asyncStub =
                CandidateFilteringServiceGrpc.newStub(channel)
                .withCallCredentials(token); // add authentication

        scoreCandidateResume(blockingStub);

        getQualifiedCandidates(asyncStub);

        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     *  UNARY METHOD TYPE
     * INPUT: A candicate's resume
	 * OUTPUT: A score for the resume
	 * rpc ScoringCandidateResume(CandidateResume) returns (ResumeScore) {}
     */

    private static void scoreCandidateResume(CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub) {
        // Create  scorinRequest
        CandidateResume scoreRequest = CandidateResume.newBuilder()
                .setCandidateName("")
                .setResumeText("")
                .addSkills("")
                .setYearsExperience(0)
                .build();

        logger.info("Send candidate resume request to the server.");

        try {
            // Call the scoringCandidateResume method & get the response
            ResumeScore response = blockingStub.scoringCandidateResume(scoreRequest);
            logger.info("Resume score received: " + response.getScore());
        } catch (StatusRuntimeException e) {
            logger.log(Level.SEVERE, "Call failed: " + e.getStatus().getDescription(), e);
        }
    }

    /**
     *  SERVER-STREAMING METHOD TYPE
     *   INPUT: Criteria to filter qualified candidates/threshold: resume scores over/equal 80 marks
     *   OUTPUT: A stream of qualified candidates
     *   rpc QualifiedCandidateList(QualificationCriteria) returns (stream QualifiedCandidate)
     */
    private static void getQualifiedCandidates(CandidateFilteringServiceGrpc.CandidateFilteringServiceStub asyncStub) {

        // create request object for filtering qualified candidates
        QualificationCriteria request = QualificationCriteria.newBuilder()
                .setMinScore(0.0) // Set some minimum score
                .build();
        logger.info("Filtering qualified candidates with the requested minimum score.");

        // Make the asynchronous call with a StreamObserver to handle the streaming responses
        asyncStub.qualifiedCandidateList(request, new StreamObserver<QualifiedCandidate>() {
            @Override
            public void onNext(QualifiedCandidate candidate) {
                // Handle each qualified candidate as it arrives
                logger.info("Received qualified candidate: " + candidate.getCandidateName() + " with score: " + candidate.getScore());
            }

            @Override
            public void onError(Throwable t) {
                // Handle errors that occur during the stream
                logger.log(Level.SEVERE, "Error during streaming", t);
            }

            @Override
            public void onCompleted() {
                // Notify when the server has finished sending responses
                logger.info("Server has finished sending responses.");
            }
        });
    }
}