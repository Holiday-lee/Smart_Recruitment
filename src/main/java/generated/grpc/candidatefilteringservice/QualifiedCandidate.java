// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CandidateFilteringService.proto

package generated.grpc.candidatefilteringservice;

/**
 * <pre>
 * Message containing details of a qualified candidate
 * </pre>
 *
 * Protobuf type {@code CandidateFilteringService.QualifiedCandidate}
 */
public  final class QualifiedCandidate extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:CandidateFilteringService.QualifiedCandidate)
    QualifiedCandidateOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QualifiedCandidate.newBuilder() to construct.
  private QualifiedCandidate(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QualifiedCandidate() {
    candidateName_ = "";
    score_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QualifiedCandidate(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            candidateName_ = s;
            break;
          }
          case 25: {

            score_ = input.readDouble();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualifiedCandidate_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualifiedCandidate_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.grpc.candidatefilteringservice.QualifiedCandidate.class, generated.grpc.candidatefilteringservice.QualifiedCandidate.Builder.class);
  }

  public static final int CANDIDATE_NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object candidateName_;
  /**
   * <code>string candidate_name = 1;</code>
   */
  public java.lang.String getCandidateName() {
    java.lang.Object ref = candidateName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      candidateName_ = s;
      return s;
    }
  }
  /**
   * <code>string candidate_name = 1;</code>
   */
  public com.google.protobuf.ByteString
      getCandidateNameBytes() {
    java.lang.Object ref = candidateName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      candidateName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SCORE_FIELD_NUMBER = 3;
  private double score_;
  /**
   * <code>double score = 3;</code>
   */
  public double getScore() {
    return score_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getCandidateNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, candidateName_);
    }
    if (score_ != 0D) {
      output.writeDouble(3, score_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getCandidateNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, candidateName_);
    }
    if (score_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, score_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof generated.grpc.candidatefilteringservice.QualifiedCandidate)) {
      return super.equals(obj);
    }
    generated.grpc.candidatefilteringservice.QualifiedCandidate other = (generated.grpc.candidatefilteringservice.QualifiedCandidate) obj;

    boolean result = true;
    result = result && getCandidateName()
        .equals(other.getCandidateName());
    result = result && (
        java.lang.Double.doubleToLongBits(getScore())
        == java.lang.Double.doubleToLongBits(
            other.getScore()));
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + CANDIDATE_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getCandidateName().hashCode();
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getScore()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.grpc.candidatefilteringservice.QualifiedCandidate parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(generated.grpc.candidatefilteringservice.QualifiedCandidate prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Message containing details of a qualified candidate
   * </pre>
   *
   * Protobuf type {@code CandidateFilteringService.QualifiedCandidate}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:CandidateFilteringService.QualifiedCandidate)
      generated.grpc.candidatefilteringservice.QualifiedCandidateOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualifiedCandidate_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualifiedCandidate_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.grpc.candidatefilteringservice.QualifiedCandidate.class, generated.grpc.candidatefilteringservice.QualifiedCandidate.Builder.class);
    }

    // Construct using generated.grpc.candidatefilteringservice.QualifiedCandidate.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      candidateName_ = "";

      score_ = 0D;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualifiedCandidate_descriptor;
    }

    @java.lang.Override
    public generated.grpc.candidatefilteringservice.QualifiedCandidate getDefaultInstanceForType() {
      return generated.grpc.candidatefilteringservice.QualifiedCandidate.getDefaultInstance();
    }

    @java.lang.Override
    public generated.grpc.candidatefilteringservice.QualifiedCandidate build() {
      generated.grpc.candidatefilteringservice.QualifiedCandidate result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.grpc.candidatefilteringservice.QualifiedCandidate buildPartial() {
      generated.grpc.candidatefilteringservice.QualifiedCandidate result = new generated.grpc.candidatefilteringservice.QualifiedCandidate(this);
      result.candidateName_ = candidateName_;
      result.score_ = score_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof generated.grpc.candidatefilteringservice.QualifiedCandidate) {
        return mergeFrom((generated.grpc.candidatefilteringservice.QualifiedCandidate)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.grpc.candidatefilteringservice.QualifiedCandidate other) {
      if (other == generated.grpc.candidatefilteringservice.QualifiedCandidate.getDefaultInstance()) return this;
      if (!other.getCandidateName().isEmpty()) {
        candidateName_ = other.candidateName_;
        onChanged();
      }
      if (other.getScore() != 0D) {
        setScore(other.getScore());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      generated.grpc.candidatefilteringservice.QualifiedCandidate parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (generated.grpc.candidatefilteringservice.QualifiedCandidate) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object candidateName_ = "";
    /**
     * <code>string candidate_name = 1;</code>
     */
    public java.lang.String getCandidateName() {
      java.lang.Object ref = candidateName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        candidateName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string candidate_name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getCandidateNameBytes() {
      java.lang.Object ref = candidateName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        candidateName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string candidate_name = 1;</code>
     */
    public Builder setCandidateName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      candidateName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string candidate_name = 1;</code>
     */
    public Builder clearCandidateName() {
      
      candidateName_ = getDefaultInstance().getCandidateName();
      onChanged();
      return this;
    }
    /**
     * <code>string candidate_name = 1;</code>
     */
    public Builder setCandidateNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      candidateName_ = value;
      onChanged();
      return this;
    }

    private double score_ ;
    /**
     * <code>double score = 3;</code>
     */
    public double getScore() {
      return score_;
    }
    /**
     * <code>double score = 3;</code>
     */
    public Builder setScore(double value) {
      
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double score = 3;</code>
     */
    public Builder clearScore() {
      
      score_ = 0D;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:CandidateFilteringService.QualifiedCandidate)
  }

  // @@protoc_insertion_point(class_scope:CandidateFilteringService.QualifiedCandidate)
  private static final generated.grpc.candidatefilteringservice.QualifiedCandidate DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.grpc.candidatefilteringservice.QualifiedCandidate();
  }

  public static generated.grpc.candidatefilteringservice.QualifiedCandidate getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QualifiedCandidate>
      PARSER = new com.google.protobuf.AbstractParser<QualifiedCandidate>() {
    @java.lang.Override
    public QualifiedCandidate parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QualifiedCandidate(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QualifiedCandidate> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QualifiedCandidate> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.grpc.candidatefilteringservice.QualifiedCandidate getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

