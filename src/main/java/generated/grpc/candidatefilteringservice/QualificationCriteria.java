// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CandidateFilteringService.proto

package generated.grpc.candidatefilteringservice;

/**
 * <pre>
 * Message for setting qualification criteria
 * </pre>
 *
 * Protobuf type {@code CandidateFilteringService.QualificationCriteria}
 */
public  final class QualificationCriteria extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:CandidateFilteringService.QualificationCriteria)
    QualificationCriteriaOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QualificationCriteria.newBuilder() to construct.
  private QualificationCriteria(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QualificationCriteria() {
    minScore_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QualificationCriteria(
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
          case 9: {

            minScore_ = input.readDouble();
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
    return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualificationCriteria_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualificationCriteria_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.grpc.candidatefilteringservice.QualificationCriteria.class, generated.grpc.candidatefilteringservice.QualificationCriteria.Builder.class);
  }

  public static final int MIN_SCORE_FIELD_NUMBER = 1;
  private double minScore_;
  /**
   * <code>double min_score = 1;</code>
   */
  public double getMinScore() {
    return minScore_;
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
    if (minScore_ != 0D) {
      output.writeDouble(1, minScore_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (minScore_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, minScore_);
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
    if (!(obj instanceof generated.grpc.candidatefilteringservice.QualificationCriteria)) {
      return super.equals(obj);
    }
    generated.grpc.candidatefilteringservice.QualificationCriteria other = (generated.grpc.candidatefilteringservice.QualificationCriteria) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getMinScore())
        == java.lang.Double.doubleToLongBits(
            other.getMinScore()));
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
    hash = (37 * hash) + MIN_SCORE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getMinScore()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.grpc.candidatefilteringservice.QualificationCriteria parseFrom(
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
  public static Builder newBuilder(generated.grpc.candidatefilteringservice.QualificationCriteria prototype) {
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
   * Message for setting qualification criteria
   * </pre>
   *
   * Protobuf type {@code CandidateFilteringService.QualificationCriteria}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:CandidateFilteringService.QualificationCriteria)
      generated.grpc.candidatefilteringservice.QualificationCriteriaOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualificationCriteria_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualificationCriteria_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.grpc.candidatefilteringservice.QualificationCriteria.class, generated.grpc.candidatefilteringservice.QualificationCriteria.Builder.class);
    }

    // Construct using generated.grpc.candidatefilteringservice.QualificationCriteria.newBuilder()
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
      minScore_ = 0D;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.grpc.candidatefilteringservice.CandidateFilteringServiceImpl.internal_static_CandidateFilteringService_QualificationCriteria_descriptor;
    }

    @java.lang.Override
    public generated.grpc.candidatefilteringservice.QualificationCriteria getDefaultInstanceForType() {
      return generated.grpc.candidatefilteringservice.QualificationCriteria.getDefaultInstance();
    }

    @java.lang.Override
    public generated.grpc.candidatefilteringservice.QualificationCriteria build() {
      generated.grpc.candidatefilteringservice.QualificationCriteria result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.grpc.candidatefilteringservice.QualificationCriteria buildPartial() {
      generated.grpc.candidatefilteringservice.QualificationCriteria result = new generated.grpc.candidatefilteringservice.QualificationCriteria(this);
      result.minScore_ = minScore_;
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
      if (other instanceof generated.grpc.candidatefilteringservice.QualificationCriteria) {
        return mergeFrom((generated.grpc.candidatefilteringservice.QualificationCriteria)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.grpc.candidatefilteringservice.QualificationCriteria other) {
      if (other == generated.grpc.candidatefilteringservice.QualificationCriteria.getDefaultInstance()) return this;
      if (other.getMinScore() != 0D) {
        setMinScore(other.getMinScore());
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
      generated.grpc.candidatefilteringservice.QualificationCriteria parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (generated.grpc.candidatefilteringservice.QualificationCriteria) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double minScore_ ;
    /**
     * <code>double min_score = 1;</code>
     */
    public double getMinScore() {
      return minScore_;
    }
    /**
     * <code>double min_score = 1;</code>
     */
    public Builder setMinScore(double value) {
      
      minScore_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double min_score = 1;</code>
     */
    public Builder clearMinScore() {
      
      minScore_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:CandidateFilteringService.QualificationCriteria)
  }

  // @@protoc_insertion_point(class_scope:CandidateFilteringService.QualificationCriteria)
  private static final generated.grpc.candidatefilteringservice.QualificationCriteria DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.grpc.candidatefilteringservice.QualificationCriteria();
  }

  public static generated.grpc.candidatefilteringservice.QualificationCriteria getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QualificationCriteria>
      PARSER = new com.google.protobuf.AbstractParser<QualificationCriteria>() {
    @java.lang.Override
    public QualificationCriteria parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QualificationCriteria(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QualificationCriteria> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QualificationCriteria> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.grpc.candidatefilteringservice.QualificationCriteria getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

