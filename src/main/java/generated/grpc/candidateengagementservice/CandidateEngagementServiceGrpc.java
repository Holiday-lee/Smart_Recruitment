package generated.grpc.candidateengagementservice;

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
    comments = "Source: CandidateEngagementService.proto")
public final class CandidateEngagementServiceGrpc {

  private CandidateEngagementServiceGrpc() {}

  public static final String SERVICE_NAME = "CandidateEngagementService.CandidateEngagementService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.SlotSelection,
      generated.grpc.candidateengagementservice.SchedulingConfirmation> getConfirmInterviewSlotMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmInterviewSlot",
      requestType = generated.grpc.candidateengagementservice.SlotSelection.class,
      responseType = generated.grpc.candidateengagementservice.SchedulingConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.SlotSelection,
      generated.grpc.candidateengagementservice.SchedulingConfirmation> getConfirmInterviewSlotMethod() {
    io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.SlotSelection, generated.grpc.candidateengagementservice.SchedulingConfirmation> getConfirmInterviewSlotMethod;
    if ((getConfirmInterviewSlotMethod = CandidateEngagementServiceGrpc.getConfirmInterviewSlotMethod) == null) {
      synchronized (CandidateEngagementServiceGrpc.class) {
        if ((getConfirmInterviewSlotMethod = CandidateEngagementServiceGrpc.getConfirmInterviewSlotMethod) == null) {
          CandidateEngagementServiceGrpc.getConfirmInterviewSlotMethod = getConfirmInterviewSlotMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.candidateengagementservice.SlotSelection, generated.grpc.candidateengagementservice.SchedulingConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "CandidateEngagementService.CandidateEngagementService", "ConfirmInterviewSlot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.SlotSelection.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.SchedulingConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new CandidateEngagementServiceMethodDescriptorSupplier("ConfirmInterviewSlot"))
                  .build();
          }
        }
     }
     return getConfirmInterviewSlotMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.ApplicationStatus,
      generated.grpc.candidateengagementservice.NotificationStatus> getSendStatusUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendStatusUpdate",
      requestType = generated.grpc.candidateengagementservice.ApplicationStatus.class,
      responseType = generated.grpc.candidateengagementservice.NotificationStatus.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.ApplicationStatus,
      generated.grpc.candidateengagementservice.NotificationStatus> getSendStatusUpdateMethod() {
    io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.ApplicationStatus, generated.grpc.candidateengagementservice.NotificationStatus> getSendStatusUpdateMethod;
    if ((getSendStatusUpdateMethod = CandidateEngagementServiceGrpc.getSendStatusUpdateMethod) == null) {
      synchronized (CandidateEngagementServiceGrpc.class) {
        if ((getSendStatusUpdateMethod = CandidateEngagementServiceGrpc.getSendStatusUpdateMethod) == null) {
          CandidateEngagementServiceGrpc.getSendStatusUpdateMethod = getSendStatusUpdateMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.candidateengagementservice.ApplicationStatus, generated.grpc.candidateengagementservice.NotificationStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CandidateEngagementService.CandidateEngagementService", "SendStatusUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.ApplicationStatus.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.NotificationStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new CandidateEngagementServiceMethodDescriptorSupplier("SendStatusUpdate"))
                  .build();
          }
        }
     }
     return getSendStatusUpdateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CandidateEngagementServiceStub newStub(io.grpc.Channel channel) {
    return new CandidateEngagementServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CandidateEngagementServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CandidateEngagementServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CandidateEngagementServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CandidateEngagementServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CandidateEngagementServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CLIENT-STREAMING METHOD TYPE
     * INPUT: A stream of interview slots
     * OUTPUT: A confirmation from the candidate
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SlotSelection> confirmInterviewSlot(
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SchedulingConfirmation> responseObserver) {
      return asyncUnimplementedStreamingCall(getConfirmInterviewSlotMethod(), responseObserver);
    }

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: Candidate ID and application status
     * OUTPUT: Notification status
     * </pre>
     */
    public void sendStatusUpdate(generated.grpc.candidateengagementservice.ApplicationStatus request,
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.NotificationStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getSendStatusUpdateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConfirmInterviewSlotMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.candidateengagementservice.SlotSelection,
                generated.grpc.candidateengagementservice.SchedulingConfirmation>(
                  this, METHODID_CONFIRM_INTERVIEW_SLOT)))
          .addMethod(
            getSendStatusUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.grpc.candidateengagementservice.ApplicationStatus,
                generated.grpc.candidateengagementservice.NotificationStatus>(
                  this, METHODID_SEND_STATUS_UPDATE)))
          .build();
    }
  }

  /**
   */
  public static final class CandidateEngagementServiceStub extends io.grpc.stub.AbstractStub<CandidateEngagementServiceStub> {
    private CandidateEngagementServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CandidateEngagementServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CandidateEngagementServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CandidateEngagementServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * CLIENT-STREAMING METHOD TYPE
     * INPUT: A stream of interview slots
     * OUTPUT: A confirmation from the candidate
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SlotSelection> confirmInterviewSlot(
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SchedulingConfirmation> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getConfirmInterviewSlotMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: Candidate ID and application status
     * OUTPUT: Notification status
     * </pre>
     */
    public void sendStatusUpdate(generated.grpc.candidateengagementservice.ApplicationStatus request,
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.NotificationStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendStatusUpdateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CandidateEngagementServiceBlockingStub extends io.grpc.stub.AbstractStub<CandidateEngagementServiceBlockingStub> {
    private CandidateEngagementServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CandidateEngagementServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CandidateEngagementServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CandidateEngagementServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: Candidate ID and application status
     * OUTPUT: Notification status
     * </pre>
     */
    public generated.grpc.candidateengagementservice.NotificationStatus sendStatusUpdate(generated.grpc.candidateengagementservice.ApplicationStatus request) {
      return blockingUnaryCall(
          getChannel(), getSendStatusUpdateMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CandidateEngagementServiceFutureStub extends io.grpc.stub.AbstractStub<CandidateEngagementServiceFutureStub> {
    private CandidateEngagementServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CandidateEngagementServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CandidateEngagementServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CandidateEngagementServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: Candidate ID and application status
     * OUTPUT: Notification status
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.candidateengagementservice.NotificationStatus> sendStatusUpdate(
        generated.grpc.candidateengagementservice.ApplicationStatus request) {
      return futureUnaryCall(
          getChannel().newCall(getSendStatusUpdateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_STATUS_UPDATE = 0;
  private static final int METHODID_CONFIRM_INTERVIEW_SLOT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CandidateEngagementServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CandidateEngagementServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_STATUS_UPDATE:
          serviceImpl.sendStatusUpdate((generated.grpc.candidateengagementservice.ApplicationStatus) request,
              (io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.NotificationStatus>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONFIRM_INTERVIEW_SLOT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.confirmInterviewSlot(
              (io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SchedulingConfirmation>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CandidateEngagementServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CandidateEngagementServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.candidateengagementservice.CandidateEngagementServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CandidateEngagementService");
    }
  }

  private static final class CandidateEngagementServiceFileDescriptorSupplier
      extends CandidateEngagementServiceBaseDescriptorSupplier {
    CandidateEngagementServiceFileDescriptorSupplier() {}
  }

  private static final class CandidateEngagementServiceMethodDescriptorSupplier
      extends CandidateEngagementServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CandidateEngagementServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CandidateEngagementServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CandidateEngagementServiceFileDescriptorSupplier())
              .addMethod(getConfirmInterviewSlotMethod())
              .addMethod(getSendStatusUpdateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
