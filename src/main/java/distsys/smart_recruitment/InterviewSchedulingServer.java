/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment;

/**
 *
 * @author jiaki
 */

import io.grpc.stub.StreamObserver;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSlot;

import java.io.IOException;

public class InterviewSchedulingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Create a new instance of the server
        Server server = ServerBuilder.forPort(8080)
                .addService(new InterviewSchedulingServiceImpl())
                .build();

        // Start the server
        server.start();
        System.out.println("Server started, listening on port 8080");

        // Keep the server running
        server.awaitTermination();
    }

    // Implementation of the InterviewSchedulingService
    static class InterviewSchedulingServiceImpl extends InterviewSchedulingServiceGrpc.InterviewSchedulingServiceImplBase {
        
        @Override
        public StreamObserver<CandidateName> arrangeInterviewSlot(StreamObserver<InterviewSlot> responseObserver) {
            return new StreamObserver<CandidateName>() {
                
                @Override
                public void onNext(CandidateName candidate) {
                    // Simulate slot assignment (you can enhance this logic)
                    System.out.println("Received candidate: " + candidate.getCandidateName());

                    // Creating a dummy interview slot
                    InterviewSlot slot = InterviewSlot.newBuilder()
                            .setTime("10:00 AM")
                            .setLocation("Zoom Meeting")
                            .build();

                    // Sending the interview slot back to the client
                    responseObserver.onNext(slot);
                }

                @Override
                public void onError(Throwable t) {
                    // Handle any errors that occur
                    System.err.println("Error in processing: " + t.getMessage());
                    responseObserver.onError(Status.INTERNAL.withDescription("Error during processing").asRuntimeException());
                }

                @Override
                public void onCompleted() {
                    // When the stream ends, we complete the response
                    responseObserver.onCompleted();
                }
            };
        }
    }
}
