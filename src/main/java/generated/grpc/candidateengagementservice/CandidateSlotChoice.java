// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CandidateEngagementService.proto

package generated.grpc.candidateengagementservice;

/**
 * <pre>
 * Message for a candidate's chosen slot (received by the recruiter side)
 * </pre>
 *
 * Protobuf type {@code CandidateEngagementService.CandidateSlotChoice}
 */
public  final class CandidateSlotChoice extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:CandidateEngagementService.CandidateSlotChoice)
    CandidateSlotChoiceOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CandidateSlotChoice.newBuilder() to construct.
  private CandidateSlotChoice(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CandidateSlotChoice() {
    candidateName_ = "";
    chosenTime_ = "";
    chosenLocation_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CandidateSlotChoice(
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
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            chosenTime_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            chosenLocation_ = s;
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
    return generated.grpc.candidateengagementservice.CandidateEngagementServiceImpl.internal_static_CandidateEngagementService_CandidateSlotChoice_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.grpc.candidateengagementservice.CandidateEngagementServiceImpl.internal_static_CandidateEngagementService_CandidateSlotChoice_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.grpc.candidateengagementservice.CandidateSlotChoice.class, generated.grpc.candidateengagementservice.CandidateSlotChoice.Builder.class);
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

  public static final int CHOSEN_TIME_FIELD_NUMBER = 2;
  private volatile java.lang.Object chosenTime_;
  /**
   * <code>string chosen_time = 2;</code>
   */
  public java.lang.String getChosenTime() {
    java.lang.Object ref = chosenTime_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      chosenTime_ = s;
      return s;
    }
  }
  /**
   * <code>string chosen_time = 2;</code>
   */
  public com.google.protobuf.ByteString
      getChosenTimeBytes() {
    java.lang.Object ref = chosenTime_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      chosenTime_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CHOSEN_LOCATION_FIELD_NUMBER = 3;
  private volatile java.lang.Object chosenLocation_;
  /**
   * <code>string chosen_location = 3;</code>
   */
  public java.lang.String getChosenLocation() {
    java.lang.Object ref = chosenLocation_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      chosenLocation_ = s;
      return s;
    }
  }
  /**
   * <code>string chosen_location = 3;</code>
   */
  public com.google.protobuf.ByteString
      getChosenLocationBytes() {
    java.lang.Object ref = chosenLocation_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      chosenLocation_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getChosenTimeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, chosenTime_);
    }
    if (!getChosenLocationBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, chosenLocation_);
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
    if (!getChosenTimeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, chosenTime_);
    }
    if (!getChosenLocationBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, chosenLocation_);
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
    if (!(obj instanceof generated.grpc.candidateengagementservice.CandidateSlotChoice)) {
      return super.equals(obj);
    }
    generated.grpc.candidateengagementservice.CandidateSlotChoice other = (generated.grpc.candidateengagementservice.CandidateSlotChoice) obj;

    boolean result = true;
    result = result && getCandidateName()
        .equals(other.getCandidateName());
    result = result && getChosenTime()
        .equals(other.getChosenTime());
    result = result && getChosenLocation()
        .equals(other.getChosenLocation());
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
    hash = (37 * hash) + CHOSEN_TIME_FIELD_NUMBER;
    hash = (53 * hash) + getChosenTime().hashCode();
    hash = (37 * hash) + CHOSEN_LOCATION_FIELD_NUMBER;
    hash = (53 * hash) + getChosenLocation().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.grpc.candidateengagementservice.CandidateSlotChoice parseFrom(
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
  public static Builder newBuilder(generated.grpc.candidateengagementservice.CandidateSlotChoice prototype) {
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
   * Message for a candidate's chosen slot (received by the recruiter side)
   * </pre>
   *
   * Protobuf type {@code CandidateEngagementService.CandidateSlotChoice}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:CandidateEngagementService.CandidateSlotChoice)
      generated.grpc.candidateengagementservice.CandidateSlotChoiceOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.grpc.candidateengagementservice.CandidateEngagementServiceImpl.internal_static_CandidateEngagementService_CandidateSlotChoice_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.grpc.candidateengagementservice.CandidateEngagementServiceImpl.internal_static_CandidateEngagementService_CandidateSlotChoice_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.grpc.candidateengagementservice.CandidateSlotChoice.class, generated.grpc.candidateengagementservice.CandidateSlotChoice.Builder.class);
    }

    // Construct using generated.grpc.candidateengagementservice.CandidateSlotChoice.newBuilder()
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

      chosenTime_ = "";

      chosenLocation_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.grpc.candidateengagementservice.CandidateEngagementServiceImpl.internal_static_CandidateEngagementService_CandidateSlotChoice_descriptor;
    }

    @java.lang.Override
    public generated.grpc.candidateengagementservice.CandidateSlotChoice getDefaultInstanceForType() {
      return generated.grpc.candidateengagementservice.CandidateSlotChoice.getDefaultInstance();
    }

    @java.lang.Override
    public generated.grpc.candidateengagementservice.CandidateSlotChoice build() {
      generated.grpc.candidateengagementservice.CandidateSlotChoice result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.grpc.candidateengagementservice.CandidateSlotChoice buildPartial() {
      generated.grpc.candidateengagementservice.CandidateSlotChoice result = new generated.grpc.candidateengagementservice.CandidateSlotChoice(this);
      result.candidateName_ = candidateName_;
      result.chosenTime_ = chosenTime_;
      result.chosenLocation_ = chosenLocation_;
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
      if (other instanceof generated.grpc.candidateengagementservice.CandidateSlotChoice) {
        return mergeFrom((generated.grpc.candidateengagementservice.CandidateSlotChoice)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.grpc.candidateengagementservice.CandidateSlotChoice other) {
      if (other == generated.grpc.candidateengagementservice.CandidateSlotChoice.getDefaultInstance()) return this;
      if (!other.getCandidateName().isEmpty()) {
        candidateName_ = other.candidateName_;
        onChanged();
      }
      if (!other.getChosenTime().isEmpty()) {
        chosenTime_ = other.chosenTime_;
        onChanged();
      }
      if (!other.getChosenLocation().isEmpty()) {
        chosenLocation_ = other.chosenLocation_;
        onChanged();
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
      generated.grpc.candidateengagementservice.CandidateSlotChoice parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (generated.grpc.candidateengagementservice.CandidateSlotChoice) e.getUnfinishedMessage();
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

    private java.lang.Object chosenTime_ = "";
    /**
     * <code>string chosen_time = 2;</code>
     */
    public java.lang.String getChosenTime() {
      java.lang.Object ref = chosenTime_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        chosenTime_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string chosen_time = 2;</code>
     */
    public com.google.protobuf.ByteString
        getChosenTimeBytes() {
      java.lang.Object ref = chosenTime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        chosenTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string chosen_time = 2;</code>
     */
    public Builder setChosenTime(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      chosenTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string chosen_time = 2;</code>
     */
    public Builder clearChosenTime() {
      
      chosenTime_ = getDefaultInstance().getChosenTime();
      onChanged();
      return this;
    }
    /**
     * <code>string chosen_time = 2;</code>
     */
    public Builder setChosenTimeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      chosenTime_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object chosenLocation_ = "";
    /**
     * <code>string chosen_location = 3;</code>
     */
    public java.lang.String getChosenLocation() {
      java.lang.Object ref = chosenLocation_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        chosenLocation_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string chosen_location = 3;</code>
     */
    public com.google.protobuf.ByteString
        getChosenLocationBytes() {
      java.lang.Object ref = chosenLocation_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        chosenLocation_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string chosen_location = 3;</code>
     */
    public Builder setChosenLocation(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      chosenLocation_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string chosen_location = 3;</code>
     */
    public Builder clearChosenLocation() {
      
      chosenLocation_ = getDefaultInstance().getChosenLocation();
      onChanged();
      return this;
    }
    /**
     * <code>string chosen_location = 3;</code>
     */
    public Builder setChosenLocationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      chosenLocation_ = value;
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


    // @@protoc_insertion_point(builder_scope:CandidateEngagementService.CandidateSlotChoice)
  }

  // @@protoc_insertion_point(class_scope:CandidateEngagementService.CandidateSlotChoice)
  private static final generated.grpc.candidateengagementservice.CandidateSlotChoice DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.grpc.candidateengagementservice.CandidateSlotChoice();
  }

  public static generated.grpc.candidateengagementservice.CandidateSlotChoice getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CandidateSlotChoice>
      PARSER = new com.google.protobuf.AbstractParser<CandidateSlotChoice>() {
    @java.lang.Override
    public CandidateSlotChoice parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CandidateSlotChoice(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CandidateSlotChoice> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CandidateSlotChoice> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.grpc.candidateengagementservice.CandidateSlotChoice getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

