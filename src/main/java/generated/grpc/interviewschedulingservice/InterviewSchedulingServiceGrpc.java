package generated.grpc.interviewschedulingservice;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: InterviewSchedulingService.proto")
public final class InterviewSchedulingServiceGrpc {

  private InterviewSchedulingServiceGrpc() {}

  public static final String SERVICE_NAME = "InterviewSchedulingService.InterviewSchedulingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.interviewschedulingservice.CandidateName,
      generated.grpc.interviewschedulingservice.InterviewSlot> getArrangeInterviewSlotMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ArrangeInterviewSlot",
      requestType = generated.grpc.interviewschedulingservice.CandidateName.class,
      responseType = generated.grpc.interviewschedulingservice.InterviewSlot.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.interviewschedulingservice.CandidateName,
      generated.grpc.interviewschedulingservice.InterviewSlot> getArrangeInterviewSlotMethod() {
    io.grpc.MethodDescriptor<generated.grpc.interviewschedulingservice.CandidateName, generated.grpc.interviewschedulingservice.InterviewSlot> getArrangeInterviewSlotMethod;
    if ((getArrangeInterviewSlotMethod = InterviewSchedulingServiceGrpc.getArrangeInterviewSlotMethod) == null) {
      synchronized (InterviewSchedulingServiceGrpc.class) {
        if ((getArrangeInterviewSlotMethod = InterviewSchedulingServiceGrpc.getArrangeInterviewSlotMethod) == null) {
          InterviewSchedulingServiceGrpc.getArrangeInterviewSlotMethod = getArrangeInterviewSlotMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.interviewschedulingservice.CandidateName, generated.grpc.interviewschedulingservice.InterviewSlot>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "InterviewSchedulingService.InterviewSchedulingService", "ArrangeInterviewSlot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.interviewschedulingservice.CandidateName.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.interviewschedulingservice.InterviewSlot.getDefaultInstance()))
                  .setSchemaDescriptor(new InterviewSchedulingServiceMethodDescriptorSupplier("ArrangeInterviewSlot"))
                  .build();
          }
        }
     }
     return getArrangeInterviewSlotMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InterviewSchedulingServiceStub newStub(io.grpc.Channel channel) {
    return new InterviewSchedulingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InterviewSchedulingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InterviewSchedulingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InterviewSchedulingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InterviewSchedulingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class InterviewSchedulingServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * BIDIRECTIONAL-STREAMING METHOD TYPE
     * INPUT: Stream of candidate name
     * OUTPUT: Stream of interview slot
     * Auto-scheduling interview slots. The client sends a stream of candidate name and the server returns(arranges) a stream of interview slot with 1 candidate have few slots to choose from
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.interviewschedulingservice.CandidateName> arrangeInterviewSlot(
        io.grpc.stub.StreamObserver<generated.grpc.interviewschedulingservice.InterviewSlot> responseObserver) {
      return asyncUnimplementedStreamingCall(getArrangeInterviewSlotMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getArrangeInterviewSlotMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                generated.grpc.interviewschedulingservice.CandidateName,
                generated.grpc.interviewschedulingservice.InterviewSlot>(
                  this, METHODID_ARRANGE_INTERVIEW_SLOT)))
          .build();
    }
  }

  /**
   */
  public static final class InterviewSchedulingServiceStub extends io.grpc.stub.AbstractStub<InterviewSchedulingServiceStub> {
    private InterviewSchedulingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterviewSchedulingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterviewSchedulingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterviewSchedulingServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * BIDIRECTIONAL-STREAMING METHOD TYPE
     * INPUT: Stream of candidate name
     * OUTPUT: Stream of interview slot
     * Auto-scheduling interview slots. The client sends a stream of candidate name and the server returns(arranges) a stream of interview slot with 1 candidate have few slots to choose from
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.interviewschedulingservice.CandidateName> arrangeInterviewSlot(
        io.grpc.stub.StreamObserver<generated.grpc.interviewschedulingservice.InterviewSlot> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getArrangeInterviewSlotMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class InterviewSchedulingServiceBlockingStub extends io.grpc.stub.AbstractStub<InterviewSchedulingServiceBlockingStub> {
    private InterviewSchedulingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterviewSchedulingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterviewSchedulingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterviewSchedulingServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class InterviewSchedulingServiceFutureStub extends io.grpc.stub.AbstractStub<InterviewSchedulingServiceFutureStub> {
    private InterviewSchedulingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterviewSchedulingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterviewSchedulingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterviewSchedulingServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ARRANGE_INTERVIEW_SLOT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InterviewSchedulingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InterviewSchedulingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ARRANGE_INTERVIEW_SLOT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.arrangeInterviewSlot(
              (io.grpc.stub.StreamObserver<generated.grpc.interviewschedulingservice.InterviewSlot>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class InterviewSchedulingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InterviewSchedulingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.interviewschedulingservice.InterviewSchedulingServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("InterviewSchedulingService");
    }
  }

  private static final class InterviewSchedulingServiceFileDescriptorSupplier
      extends InterviewSchedulingServiceBaseDescriptorSupplier {
    InterviewSchedulingServiceFileDescriptorSupplier() {}
  }

  private static final class InterviewSchedulingServiceMethodDescriptorSupplier
      extends InterviewSchedulingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    InterviewSchedulingServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (InterviewSchedulingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InterviewSchedulingServiceFileDescriptorSupplier())
              .addMethod(getArrangeInterviewSlotMethod())
              .build();
        }
      }
    }
    return result;
  }
}
