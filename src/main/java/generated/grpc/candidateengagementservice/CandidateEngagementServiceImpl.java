// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CandidateEngagementService.proto

package generated.grpc.candidateengagementservice;

public final class CandidateEngagementServiceImpl {
  private CandidateEngagementServiceImpl() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CandidateEngagementService_SlotSelection_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CandidateEngagementService_SlotSelection_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CandidateEngagementService_CandidateSlotChoice_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CandidateEngagementService_CandidateSlotChoice_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CandidateEngagementService_SlotDeliveryConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CandidateEngagementService_SlotDeliveryConfirmation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CandidateEngagementService_SchedulingConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CandidateEngagementService_SchedulingConfirmation_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n CandidateEngagementService.proto\022\032Cand" +
      "idateEngagementService\"Q\n\rSlotSelection\022" +
      "\026\n\016candidate_name\030\001 \001(\t\022\021\n\tslot_time\030\002 \001" +
      "(\t\022\025\n\rslot_location\030\003 \001(\t\"[\n\023CandidateSl" +
      "otChoice\022\026\n\016candidate_name\030\001 \001(\t\022\023\n\013chos" +
      "en_time\030\002 \001(\t\022\027\n\017chosen_location\030\003 \001(\t\"J" +
      "\n\030SlotDeliveryConfirmation\022\027\n\017slots_deli" +
      "vered\030\001 \001(\010\022\025\n\rdelivery_time\030\002 \001(\t\"F\n\026Sc" +
      "hedulingConfirmation\022\021\n\tconfirmed\030\001 \001(\010\022" +
      "\031\n\021confirmation_time\030\002 \001(\t2\235\002\n\032Candidate" +
      "EngagementService\022y\n\022SendInterviewSlots\022" +
      ").CandidateEngagementService.SlotSelecti" +
      "on\0324.CandidateEngagementService.SlotDeli" +
      "veryConfirmation\"\000(\001\022\203\001\n\032ReceiveCandidat" +
      "eSlotChoice\022/.CandidateEngagementService" +
      ".CandidateSlotChoice\0322.CandidateEngageme" +
      "ntService.SchedulingConfirmation\"\000BM\n)ge" +
      "nerated.grpc.candidateengagementserviceB" +
      "\036CandidateEngagementServiceImplP\001b\006proto" +
      "3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_CandidateEngagementService_SlotSelection_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CandidateEngagementService_SlotSelection_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CandidateEngagementService_SlotSelection_descriptor,
        new java.lang.String[] { "CandidateName", "SlotTime", "SlotLocation", });
    internal_static_CandidateEngagementService_CandidateSlotChoice_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_CandidateEngagementService_CandidateSlotChoice_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CandidateEngagementService_CandidateSlotChoice_descriptor,
        new java.lang.String[] { "CandidateName", "ChosenTime", "ChosenLocation", });
    internal_static_CandidateEngagementService_SlotDeliveryConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_CandidateEngagementService_SlotDeliveryConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CandidateEngagementService_SlotDeliveryConfirmation_descriptor,
        new java.lang.String[] { "SlotsDelivered", "DeliveryTime", });
    internal_static_CandidateEngagementService_SchedulingConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_CandidateEngagementService_SchedulingConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CandidateEngagementService_SchedulingConfirmation_descriptor,
        new java.lang.String[] { "Confirmed", "ConfirmationTime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
