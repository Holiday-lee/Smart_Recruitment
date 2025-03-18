package generated.grpc.candidatefilteringservice;

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
    comments = "Source: CandidateFilteringService.proto")
public final class CandidateFilteringServiceGrpc {

  private CandidateFilteringServiceGrpc() {}

  public static final String SERVICE_NAME = "CandidateFilteringService.CandidateFilteringService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.grpc.candidatefilteringservice.CandidateResume,
      generated.grpc.candidatefilteringservice.ResumeScore> getScoringCandidateResumeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ScoringCandidateResume",
      requestType = generated.grpc.candidatefilteringservice.CandidateResume.class,
      responseType = generated.grpc.candidatefilteringservice.ResumeScore.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.grpc.candidatefilteringservice.CandidateResume,
      generated.grpc.candidatefilteringservice.ResumeScore> getScoringCandidateResumeMethod() {
    io.grpc.MethodDescriptor<generated.grpc.candidatefilteringservice.CandidateResume, generated.grpc.candidatefilteringservice.ResumeScore> getScoringCandidateResumeMethod;
    if ((getScoringCandidateResumeMethod = CandidateFilteringServiceGrpc.getScoringCandidateResumeMethod) == null) {
      synchronized (CandidateFilteringServiceGrpc.class) {
        if ((getScoringCandidateResumeMethod = CandidateFilteringServiceGrpc.getScoringCandidateResumeMethod) == null) {
          CandidateFilteringServiceGrpc.getScoringCandidateResumeMethod = getScoringCandidateResumeMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.candidatefilteringservice.CandidateResume, generated.grpc.candidatefilteringservice.ResumeScore>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CandidateFilteringService.CandidateFilteringService", "ScoringCandidateResume"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidatefilteringservice.CandidateResume.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidatefilteringservice.ResumeScore.getDefaultInstance()))
                  .setSchemaDescriptor(new CandidateFilteringServiceMethodDescriptorSupplier("ScoringCandidateResume"))
                  .build();
          }
        }
     }
     return getScoringCandidateResumeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.grpc.candidatefilteringservice.QualificationCriteria,
      generated.grpc.candidatefilteringservice.QualifiedCandidate> getQualifiedCandidateListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QualifiedCandidateList",
      requestType = generated.grpc.candidatefilteringservice.QualificationCriteria.class,
      responseType = generated.grpc.candidatefilteringservice.QualifiedCandidate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<generated.grpc.candidatefilteringservice.QualificationCriteria,
      generated.grpc.candidatefilteringservice.QualifiedCandidate> getQualifiedCandidateListMethod() {
    io.grpc.MethodDescriptor<generated.grpc.candidatefilteringservice.QualificationCriteria, generated.grpc.candidatefilteringservice.QualifiedCandidate> getQualifiedCandidateListMethod;
    if ((getQualifiedCandidateListMethod = CandidateFilteringServiceGrpc.getQualifiedCandidateListMethod) == null) {
      synchronized (CandidateFilteringServiceGrpc.class) {
        if ((getQualifiedCandidateListMethod = CandidateFilteringServiceGrpc.getQualifiedCandidateListMethod) == null) {
          CandidateFilteringServiceGrpc.getQualifiedCandidateListMethod = getQualifiedCandidateListMethod = 
              io.grpc.MethodDescriptor.<generated.grpc.candidatefilteringservice.QualificationCriteria, generated.grpc.candidatefilteringservice.QualifiedCandidate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "CandidateFilteringService.CandidateFilteringService", "QualifiedCandidateList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidatefilteringservice.QualificationCriteria.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.grpc.candidatefilteringservice.QualifiedCandidate.getDefaultInstance()))
                  .setSchemaDescriptor(new CandidateFilteringServiceMethodDescriptorSupplier("QualifiedCandidateList"))
                  .build();
          }
        }
     }
     return getQualifiedCandidateListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CandidateFilteringServiceStub newStub(io.grpc.Channel channel) {
    return new CandidateFilteringServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CandidateFilteringServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CandidateFilteringServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CandidateFilteringServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CandidateFilteringServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CandidateFilteringServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: A candicate's resume
     * OUTPUT: A score for the resume
     * </pre>
     */
    public void scoringCandidateResume(generated.grpc.candidatefilteringservice.CandidateResume request,
        io.grpc.stub.StreamObserver<generated.grpc.candidatefilteringservice.ResumeScore> responseObserver) {
      asyncUnimplementedUnaryCall(getScoringCandidateResumeMethod(), responseObserver);
    }

    /**
     * <pre>
     * SERVER-STREAMING METHOD TYPE
     * INPUT: Criteria to filter qualified candidates/threshold: resume scores over/equal 80 marks
     * OUTPUT: A stream of qualified candidates
     * </pre>
     */
    public void qualifiedCandidateList(generated.grpc.candidatefilteringservice.QualificationCriteria request,
        io.grpc.stub.StreamObserver<generated.grpc.candidatefilteringservice.QualifiedCandidate> responseObserver) {
      asyncUnimplementedUnaryCall(getQualifiedCandidateListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getScoringCandidateResumeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.grpc.candidatefilteringservice.CandidateResume,
                generated.grpc.candidatefilteringservice.ResumeScore>(
                  this, METHODID_SCORING_CANDIDATE_RESUME)))
          .addMethod(
            getQualifiedCandidateListMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                generated.grpc.candidatefilteringservice.QualificationCriteria,
                generated.grpc.candidatefilteringservice.QualifiedCandidate>(
                  this, METHODID_QUALIFIED_CANDIDATE_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class CandidateFilteringServiceStub extends io.grpc.stub.AbstractStub<CandidateFilteringServiceStub> {
    private CandidateFilteringServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CandidateFilteringServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CandidateFilteringServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CandidateFilteringServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: A candicate's resume
     * OUTPUT: A score for the resume
     * </pre>
     */
    public void scoringCandidateResume(generated.grpc.candidatefilteringservice.CandidateResume request,
        io.grpc.stub.StreamObserver<generated.grpc.candidatefilteringservice.ResumeScore> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getScoringCandidateResumeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * SERVER-STREAMING METHOD TYPE
     * INPUT: Criteria to filter qualified candidates/threshold: resume scores over/equal 80 marks
     * OUTPUT: A stream of qualified candidates
     * </pre>
     */
    public void qualifiedCandidateList(generated.grpc.candidatefilteringservice.QualificationCriteria request,
        io.grpc.stub.StreamObserver<generated.grpc.candidatefilteringservice.QualifiedCandidate> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getQualifiedCandidateListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CandidateFilteringServiceBlockingStub extends io.grpc.stub.AbstractStub<CandidateFilteringServiceBlockingStub> {
    private CandidateFilteringServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CandidateFilteringServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CandidateFilteringServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CandidateFilteringServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: A candicate's resume
     * OUTPUT: A score for the resume
     * </pre>
     */
    public generated.grpc.candidatefilteringservice.ResumeScore scoringCandidateResume(generated.grpc.candidatefilteringservice.CandidateResume request) {
      return blockingUnaryCall(
          getChannel(), getScoringCandidateResumeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * SERVER-STREAMING METHOD TYPE
     * INPUT: Criteria to filter qualified candidates/threshold: resume scores over/equal 80 marks
     * OUTPUT: A stream of qualified candidates
     * </pre>
     */
    public java.util.Iterator<generated.grpc.candidatefilteringservice.QualifiedCandidate> qualifiedCandidateList(
        generated.grpc.candidatefilteringservice.QualificationCriteria request) {
      return blockingServerStreamingCall(
          getChannel(), getQualifiedCandidateListMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CandidateFilteringServiceFutureStub extends io.grpc.stub.AbstractStub<CandidateFilteringServiceFutureStub> {
    private CandidateFilteringServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CandidateFilteringServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CandidateFilteringServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CandidateFilteringServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * UNARY METHOD TYPE
     * INPUT: A candicate's resume
     * OUTPUT: A score for the resume
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.grpc.candidatefilteringservice.ResumeScore> scoringCandidateResume(
        generated.grpc.candidatefilteringservice.CandidateResume request) {
      return futureUnaryCall(
          getChannel().newCall(getScoringCandidateResumeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SCORING_CANDIDATE_RESUME = 0;
  private static final int METHODID_QUALIFIED_CANDIDATE_LIST = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CandidateFilteringServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CandidateFilteringServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SCORING_CANDIDATE_RESUME:
          serviceImpl.scoringCandidateResume((generated.grpc.candidatefilteringservice.CandidateResume) request,
              (io.grpc.stub.StreamObserver<generated.grpc.candidatefilteringservice.ResumeScore>) responseObserver);
          break;
        case METHODID_QUALIFIED_CANDIDATE_LIST:
          serviceImpl.qualifiedCandidateList((generated.grpc.candidatefilteringservice.QualificationCriteria) request,
              (io.grpc.stub.StreamObserver<generated.grpc.candidatefilteringservice.QualifiedCandidate>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CandidateFilteringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CandidateFilteringServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CandidateFilteringService");
    }
  }

  private static final class CandidateFilteringServiceFileDescriptorSupplier
      extends CandidateFilteringServiceBaseDescriptorSupplier {
    CandidateFilteringServiceFileDescriptorSupplier() {}
  }

  private static final class CandidateFilteringServiceMethodDescriptorSupplier
      extends CandidateFilteringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CandidateFilteringServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CandidateFilteringServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CandidateFilteringServiceFileDescriptorSupplier())
              .addMethod(getScoringCandidateResumeMethod())
              .addMethod(getQualifiedCandidateListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
