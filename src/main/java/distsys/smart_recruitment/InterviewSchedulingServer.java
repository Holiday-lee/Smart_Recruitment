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
import generated.grpc.interviewschedulingservice.InterviewSchedulingServiceGrpc;
import generated.grpc.interviewschedulingservice.CandidateName;
import generated.grpc.interviewschedulingservice.InterviewSlot;

import java.io.IOException;
import java.util.logging.Logger;

public class InterviewSchedulingServer extends InterviewSchedulingServiceGrpc.InterviewSchedulingServiceImplBase {

    private static final Logger logger = Logger.getLogger(InterviewSchedulingServer.class.getName());

    public static void main(String[] args){
        InterviewSchedulingServer interviewSchedulingServer = new InterviewSchedulingServer();

        int port = 50052;

        try{
            Server server = ServerBuilder.forPort(port)
                    .addService(interviewSchedulingServer)
                    .build()
                    .start();
                    logger.info("Server started, listening on " + port);
                    System.out.println("Server started, listening on " + port);
                    server.awaitTermination();
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // BIDIRECTIONAL-STREAMING METHOD TYPE
    // INPUT: Stream of candidate name
    // OUTPUT: Stream of interview slot
    // Auto-scheduling interview slots. The client sends a stream of candidate name and the server returns(arranges) a stream of interview slot with 1 candidate have few slots to choose from
    //rpc ArrangeInterviewSlot(stream CandidateName) returns (stream InterviewSlot) {}


    public StreamObserver<CandidateName> arrangeInterviewSlot(StreamObserver<InterviewSlot> responseObserver) {
        return new StreamObserver<CandidateName>(){

            @Override
            public void onNext(CandidateName candidate){
                System.out.println("Received candidate: " + candidate.getCandidateName());

                // Creating interview slot
                InterviewSlot slot = InterviewSlot.newBuilder()
                        .setTime("")
                        .setLocation("")
                        .build();

                responseObserver.onNext(slot);
            }

            @Override
            public void onError(Throwable t){
                System.err.println("Error during stream processing: " + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted(){
                responseObserver.onCompleted();
            }

        };
    }

}
