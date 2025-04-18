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
 * <pre>
 * CandidateEngagementService sends interview slots to candidates and confirms delivery
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: CandidateEngagementService.proto")
public final class CandidateEngagementServiceGrpc {

  private CandidateEngagementServiceGrpc() {}

  public static final String SERVICE_NAME = "CandidateEngagementService.CandidateEngagementService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.SlotSelection,
      generated.grpc.candidateengagementservice.SlotDeliveryConfirmation> getSendInterviewSlotsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendInterviewSlots",
      requestType = generated.grpc.candidateengagementservice.SlotSelection.class,
      responseType = generated.grpc.candidateengagementservice.SlotDeliveryConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.SlotSelection,
      generated.grpc.candidateengagementservice.SlotDeliveryConfirmation> getSendInterviewSlotsMethod() {
    io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.SlotSelection, generated.grpc.candidateengagementservice.SlotDeliveryConfirmation> getSendInterviewSlotsMethod;
    if ((getSendInterviewSlotsMethod = CandidateEngagementServiceGrpc.getSendInterviewSlotsMethod) == null) {
      synchronized (CandidateEngagementServiceGrpc.class) {
        if ((getSendInterviewSlotsMethod = CandidateEngagementServiceGrpc.getSendInterviewSlotsMethod) == null) {
          CandidateEngagementServiceGrpc.getSendInterviewSlotsMethod = getSendInterviewSlotsMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.candidateengagementservice.SlotSelection, generated.grpc.candidateengagementservice.SlotDeliveryConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "CandidateEngagementService.CandidateEngagementService", "SendInterviewSlots"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.SlotSelection.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.SlotDeliveryConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new CandidateEngagementServiceMethodDescriptorSupplier("SendInterviewSlots"))
                  .build();
          }
        }
     }
     return getSendInterviewSlotsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.CandidateSlotChoice,
      generated.grpc.candidateengagementservice.SchedulingConfirmation> getSubmitSelectedSlotMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitSelectedSlot",
      requestType = generated.grpc.candidateengagementservice.CandidateSlotChoice.class,
      responseType = generated.grpc.candidateengagementservice.SchedulingConfirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.CandidateSlotChoice,
      generated.grpc.candidateengagementservice.SchedulingConfirmation> getSubmitSelectedSlotMethod() {
    io.grpc.MethodDescriptor<generated.grpc.candidateengagementservice.CandidateSlotChoice, generated.grpc.candidateengagementservice.SchedulingConfirmation> getSubmitSelectedSlotMethod;
    if ((getSubmitSelectedSlotMethod = CandidateEngagementServiceGrpc.getSubmitSelectedSlotMethod) == null) {
      synchronized (CandidateEngagementServiceGrpc.class) {
        if ((getSubmitSelectedSlotMethod = CandidateEngagementServiceGrpc.getSubmitSelectedSlotMethod) == null) {
          CandidateEngagementServiceGrpc.getSubmitSelectedSlotMethod = getSubmitSelectedSlotMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.candidateengagementservice.CandidateSlotChoice, generated.grpc.candidateengagementservice.SchedulingConfirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CandidateEngagementService.CandidateEngagementService", "SubmitSelectedSlot"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.CandidateSlotChoice.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidateengagementservice.SchedulingConfirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new CandidateEngagementServiceMethodDescriptorSupplier("SubmitSelectedSlot"))
                  .build();
          }
        }
     }
     return getSubmitSelectedSlotMethod;
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
   * <pre>
   * CandidateEngagementService sends interview slots to candidates and confirms delivery
   * </pre>
   */
  public static abstract class CandidateEngagementServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CLIENT-STREAMING METHOD
     * INPUT: A stream of interview slots for a candidate
     * OUTPUT: Confirmation that slots were successfully sent to the candidate
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SlotSelection> sendInterviewSlots(
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SlotDeliveryConfirmation> responseObserver) {
      return asyncUnimplementedStreamingCall(getSendInterviewSlotsMethod(), responseObserver);
    }

    /**
     * <pre>
     * UNARY METHOD
     * For candidate to later submit their selection (separate call)
     * </pre>
     */
    public void submitSelectedSlot(generated.grpc.candidateengagementservice.CandidateSlotChoice request,
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SchedulingConfirmation> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitSelectedSlotMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendInterviewSlotsMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                generated.grpc.candidateengagementservice.SlotSelection,
                generated.grpc.candidateengagementservice.SlotDeliveryConfirmation>(
                  this, METHODID_SEND_INTERVIEW_SLOTS)))
          .addMethod(
            getSubmitSelectedSlotMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.grpc.candidateengagementservice.CandidateSlotChoice,
                generated.grpc.candidateengagementservice.SchedulingConfirmation>(
                  this, METHODID_SUBMIT_SELECTED_SLOT)))
          .build();
    }
  }

  /**
   * <pre>
   * CandidateEngagementService sends interview slots to candidates and confirms delivery
   * </pre>
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
     * CLIENT-STREAMING METHOD
     * INPUT: A stream of interview slots for a candidate
     * OUTPUT: Confirmation that slots were successfully sent to the candidate
     * </pre>
     */
    public io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SlotSelection> sendInterviewSlots(
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SlotDeliveryConfirmation> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSendInterviewSlotsMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * UNARY METHOD
     * For candidate to later submit their selection (separate call)
     * </pre>
     */
    public void submitSelectedSlot(generated.grpc.candidateengagementservice.CandidateSlotChoice request,
        io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SchedulingConfirmation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitSelectedSlotMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * CandidateEngagementService sends interview slots to candidates and confirms delivery
   * </pre>
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
     * UNARY METHOD
     * For candidate to later submit their selection (separate call)
     * </pre>
     */
    public generated.grpc.candidateengagementservice.SchedulingConfirmation submitSelectedSlot(generated.grpc.candidateengagementservice.CandidateSlotChoice request) {
      return blockingUnaryCall(
          getChannel(), getSubmitSelectedSlotMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * CandidateEngagementService sends interview slots to candidates and confirms delivery
   * </pre>
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
     * UNARY METHOD
     * For candidate to later submit their selection (separate call)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.candidateengagementservice.SchedulingConfirmation> submitSelectedSlot(
        generated.grpc.candidateengagementservice.CandidateSlotChoice request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitSelectedSlotMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBMIT_SELECTED_SLOT = 0;
  private static final int METHODID_SEND_INTERVIEW_SLOTS = 1;

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
        case METHODID_SUBMIT_SELECTED_SLOT:
          serviceImpl.submitSelectedSlot((generated.grpc.candidateengagementservice.CandidateSlotChoice) request,
              (io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SchedulingConfirmation>) responseObserver);
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
        case METHODID_SEND_INTERVIEW_SLOTS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendInterviewSlots(
              (io.grpc.stub.StreamObserver<generated.grpc.candidateengagementservice.SlotDeliveryConfirmation>) responseObserver);
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
              .addMethod(getSendInterviewSlotsMethod())
              .addMethod(getSubmitSelectedSlotMethod())
              .build();
        }
      }
    }
    return result;
  }
}
