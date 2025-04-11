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
import io.grpc.stub.StreamObserver;
import generated.grpc.candidatefilteringservice.CandidateFilteringServiceGrpc;
import generated.grpc.candidatefilteringservice.CandidateResume;
import generated.grpc.candidatefilteringservice.ResumeScore;
import generated.grpc.candidatefilteringservice.QualificationCriteria;
import generated.grpc.candidatefilteringservice.QualifiedCandidate;

public class CandidateFilteringClient {

    public static void main(String[] args) {
        // Create a channel to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        // Create a stub for the service
        CandidateFilteringServiceGrpc.CandidateFilteringServiceBlockingStub blockingStub = CandidateFilteringServiceGrpc.newBlockingStub(channel);
        CandidateFilteringServiceGrpc.CandidateFilteringServiceStub asyncStub = CandidateFilteringServiceGrpc.newStub(channel);

        // Call the unary ScoringCandidateResume RPC
        CandidateResume resume = CandidateResume.newBuilder()
                .setCandidateId("1")
                .setCandidateName("Alice")
                .setResumeText("Experienced Java and Python developer")
                .addSkills("Java")
                .addSkills("Python")
                .setYearsExperience(5)
                .build();

        ResumeScore score = blockingStub.scoringCandidateResume(resume);
        System.out.println("Score for candidate " + resume.getCandidateName() + ": " + score.getScore());

        // Call the server-streaming QualifiedCandidateList RPC
        QualificationCriteria criteria = QualificationCriteria.newBuilder()
                .setMinScore(80.0)
                .build();

        StreamObserver<QualifiedCandidate> responseObserver = new StreamObserver<QualifiedCandidate>() {
            @Override
            public void onNext(QualifiedCandidate value) {
                System.out.println("Qualified candidate: " + value.getCandidateName() + " with score " + value.getScore());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error during stream: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed receiving qualified candidates.");
            }
        };

        asyncStub.qualifiedCandidateList(criteria, responseObserver);

        // Keep the client alive for a while to receive streaming responses
        try {
            Thread.sleep(5000); // Adjust based on expected response time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown the channel
        channel.shutdownNow();
    }
}
