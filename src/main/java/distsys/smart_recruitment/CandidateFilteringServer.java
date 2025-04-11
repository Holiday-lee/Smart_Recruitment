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
import io.grpc.stub.StreamObserver;
import generated.grpc.candidatefilteringservice.CandidateFilteringServiceGrpc;
import generated.grpc.candidatefilteringservice.CandidateResume;
import generated.grpc.candidatefilteringservice.ResumeScore;
import generated.grpc.candidatefilteringservice.QualificationCriteria;
import generated.grpc.candidatefilteringservice.QualifiedCandidate;

import java.io.IOException;

public class CandidateFilteringServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Create and start the gRPC server
        Server server = ServerBuilder.forPort(8080)
                .addService(new CandidateFilteringServiceImpl())
                .build();

        server.start();
        System.out.println("Server started on port 8080");

        // Keep the server alive to listen for requests
        server.awaitTermination();
    }

    static class CandidateFilteringServiceImpl extends CandidateFilteringServiceGrpc.CandidateFilteringServiceImplBase {

        // Unary RPC to score the candidate's resume
        @Override
        public void scoringCandidateResume(CandidateResume request, StreamObserver<ResumeScore> responseObserver) {
            // Simulate scoring the resume (you can implement real scoring logic here)
            double score = calculateScore(request);

            // Create a ResumeScore response
            ResumeScore response = ResumeScore.newBuilder()
                    .setCandidateId(request.getCandidateId())
                    .setScore(score)
                    .build();

            // Send the response back to the client
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        // Server-streaming RPC to return qualified candidates based on score criteria
        @Override
        public void qualifiedCandidateList(QualificationCriteria request, StreamObserver<QualifiedCandidate> responseObserver) {
            // Simulate fetching qualified candidates (you would use real data here)
            // For demo purposes, let's assume the candidates have scores pre-calculated

            String[] candidateNames = {"Alice", "Bob", "Charlie"};
            double[] candidateScores = {85.0, 90.0, 70.0};

            // Stream qualified candidates (those with score >= min_score)
            for (int i = 0; i < candidateNames.length; i++) {
                if (candidateScores[i] >= request.getMinScore()) {
                    QualifiedCandidate candidate = QualifiedCandidate.newBuilder()
                            .setCandidateId(String.valueOf(i))
                            .setCandidateName(candidateNames[i])
                            .setScore(candidateScores[i])
                            .build();

                    responseObserver.onNext(candidate);
                }
            }

            // End the stream
            responseObserver.onCompleted();
        }

        // Dummy scoring logic based on resume content (this can be more sophisticated)
        private double calculateScore(CandidateResume resume) {
            double score = 0.0;

            // Score based on skills and experience (for demo purposes)
            if (resume.getSkillsList().contains("Java")) score += 30;
            if (resume.getSkillsList().contains("Python")) score += 25;
            score += resume.getYearsExperience() * 5; // Adding points for experience

            return score;
        }
    }
}

