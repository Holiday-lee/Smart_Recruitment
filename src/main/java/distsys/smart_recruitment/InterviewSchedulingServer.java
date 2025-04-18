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
import distsys.smart_recruitment.auth.Constants;
import io.grpc.stub.StreamObserver;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSlot;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class InterviewSchedulingServer extends InterviewSchedulingServiceGrpc.InterviewSchedulingServiceImplBase {

    private static final Logger logger = Logger.getLogger(InterviewSchedulingServer.class.getName());
    private static final Random random = new Random();

    public static void main(String[] args) {
        InterviewSchedulingServer interviewSchedulingServer = new InterviewSchedulingServer();

        int port = 50052;

        try {
            Server server = ServerBuilder.forPort(port)
                    .addService(interviewSchedulingServer)
                    .intercept(new AuthorizationServerInterceptor()) // Add JWT authentication interceptor
                    .build()
                    .start();
            logger.info("Server started, listening on " + port);
            server.awaitTermination();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // BIDIRECTIONAL-STREAMING METHOD TYPE
    // INPUT: Stream of candidate name
    // OUTPUT: Stream of interview slot
    @Override
    public StreamObserver<CandidateName> arrangeInterviewSlot(StreamObserver<InterviewSlot> responseObserver) {

        return new StreamObserver<CandidateName>() {

            @Override
            public void onNext(CandidateName candidate) {
                logger.info("Received candidate: " + candidate.getCandidateName() + " )");

                // Generate 3-5 interview slots for the candidate
                List<InterviewSlot> slots = generateInterviewSlots(candidate.getCandidateName());
                
                // Send each slot as a separate response
                for (InterviewSlot slot : slots) {
                    responseObserver.onNext(slot);
                    logger.info("Sent interview slot: " + slot.getTime() + " at " + slot.getLocation());
                    
                    // Simulate some delay between sending slots
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        logger.warning("Interrupted during sleep: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                logger.info("Error during stream processing: " + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                logger.info("Client completed sending candidates, completing response stream");
                responseObserver.onCompleted();
            }
        };
    }
    
    // Generate a list of interview slots for a candidate
    private List<InterviewSlot> generateInterviewSlots(String candidateName) {
        List<InterviewSlot> slots = new ArrayList<>();
        
        // Generate between 3 and 5 slots
        int numberOfSlots = 3 + random.nextInt(3);
        
        // Possible interview locations
        String[] locations = {
            "Conference Room A", 
            "Meeting Room B", 
            "Interview Room 101", 
            "HR Office", 
            "Zoom Meeting (Virtual)", 
            "Microsoft Teams (Virtual)",
            "Main Office - Floor 3"
        };
        
        // Get current date
        LocalDate startDate = LocalDate.now().plusDays(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Generate slots for the next 7 days, starting from 9 AM to 4 PM
        for (int i = 0; i < numberOfSlots; i++) {
            // Pick a random day within the next 7 days
            LocalDate interviewDate = startDate.plusDays(random.nextInt(7));
            
            // Pick a random time between 9 AM and 4 PM
            int hour = 9 + random.nextInt(7);
            int minute = random.nextInt(4) * 15; // 0, 15, 30, or 45 minutes
            
            LocalTime interviewTime = LocalTime.of(hour, minute);
            String timeStr = interviewDate.format(dateFormatter) + " " + 
                    interviewTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            
            // Pick a random location
            String location = locations[random.nextInt(locations.length)];
            
            // Create the slot
            InterviewSlot slot = InterviewSlot.newBuilder()
                    .setTime(timeStr)
                    .setLocation(location)
                    .build();
            
            slots.add(slot);
        }
        
        return slots;
    }
}