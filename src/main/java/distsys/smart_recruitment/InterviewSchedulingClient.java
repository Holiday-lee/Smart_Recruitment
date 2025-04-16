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
import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSlot;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterviewSchedulingClient {

    private static final Logger logger = Logger.getLogger(InterviewSchedulingClient.class.getName());


    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 50052;

        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(host, port)
                .usePlaintext()
                .build();

    // BIDIRECTIONAL-STREAMING METHOD TYPE
    // INPUT: Stream of candidate name
    // OUTPUT: Stream of interview slot

         // Create the asynchronous stub for BI-Directional
        InterviewSchedulingServiceGrpc.InterviewSchedulingServiceStub asyncStub = InterviewSchedulingServiceGrpc.newStub(channel);

        // Create a stream observer to handle the response(interviewSlot) stream
        StreamObserver<InterviewSlot> responseObserver = new StreamObserver<InterviewSlot>() {
            @Override
            public void onNext(InterviewSlot value) {
                // Print the received interview slot
                logger.info("Received slot: " + value.getTime() + " at " + value.getLocation());
            }

            @Override
            public void onError(Throwable t) {
                // Handle error
                logger.log(Level.SEVERE, "Error receiving interview slots: " + t.getMessage(), t);
            }

            @Override
            public void onCompleted() {
                // Handle the completion of the stream
                logger.info("Completed receiving interview slots.");
            }
        };

        // Create the stream observer for sending candidate names
        // Bi-dire => asyncStub(no need block & wait)
        StreamObserver<CandidateName> requestObserver = asyncStub.arrangeInterviewSlot(responseObserver);

        try {
            // Send a candidate name with empty values for testing

           logger.info("Sending candidate names nd request for scheduling interview slots");
           CandidateName candidate = CandidateName.newBuilder()
			   .setCandidateId("")
			   .setCandidateName("")
			   .build();
			requestObserver.onNext(candidate);

            // End of the stream
            requestObserver.onCompleted();
            logger.info("Done sending candidate names, waiting for interview slots response");

        } catch (Exception e) {
            // Handle exception
            logger.log(Level.SEVERE, "Error during request stream: " + e.getMessage(), e);
            requestObserver.onError(e);
        }

        // Keep the client running to receive responses
        try {
            Thread.sleep(5000); // Wait for responses to come in
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error while sleeping: " + e.getMessage(), e);
        }

        // Shutdown the channel
        logger.info("Shutting down the channel...");
        channel.shutdownNow();
    }
}