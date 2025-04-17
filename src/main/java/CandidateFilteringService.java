/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jiaki
 */
import distsys.smart_recruitment.auth.BearerToken;
import distsys.smart_recruitment.auth.JwtUtil;
import generated.grpc.candidatefilteringservice.CandidateFilteringServiceGrpc;
import generated.grpc.candidatefilteringservice.QualificationCriteria;
import generated.grpc.candidatefilteringservice.QualifiedCandidate;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Service client for Candidate Filtering Service
 * @author jiaki
 */
public class CandidateFilteringService {
    private final CandidateFilteringServiceGrpc.CandidateFilteringServiceStub asyncStub;

    public CandidateFilteringService() {
        // Connect to the gRPC service
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051) // Use your service port
                .usePlaintext()
                .build();

        // Generate JWT token
        String jwt = JwtUtil.generateToken("QualifiedCandidatesGUI");

        // Create authentication credentials
        BearerToken token = new BearerToken(jwt);

        // Create the async stub with authentication
        asyncStub = CandidateFilteringServiceGrpc.newStub(channel)
                .withCallCredentials(token);
    }

    /**
     * Get qualified candidates with minimum score
     */
    public void getQualifiedCandidates(double minScore, final ResultCallback callback) {
        // Create the request with the minimum score
        QualificationCriteria request = QualificationCriteria.newBuilder()
                .setMinScore(minScore)
                .build();

        // Call the service and handle streaming response
        asyncStub.qualifiedCandidateList(request, new StreamObserver<QualifiedCandidate>() {
            @Override
            public void onNext(QualifiedCandidate candidate) {
                // For each candidate received, call the callback
                callback.onCandidateReceived(
                        candidate.getCandidateId(),
                        candidate.getCandidateName(),
                        candidate.getScore()
                );
            }

            @Override
            public void onError(Throwable t) {
                callback.onError("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                callback.onCompleted();
            }
        });
    }

    // Callback interface for handling results
    public interface ResultCallback {
        void onCandidateReceived(String id, String name, double score);
        void onError(String errorMessage);
        void onCompleted();
    }
}

