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
    internal_static_CandidateEngagementService_ApplicationStatus_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CandidateEngagementService_ApplicationStatus_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CandidateEngagementService_SchedulingConfirmation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CandidateEngagementService_SchedulingConfirmation_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CandidateEngagementService_NotificationStatus_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CandidateEngagementService_NotificationStatus_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n CandidateEngagementService.proto\022\032Cand" +
      "idateEngagementService\"W\n\rSlotSelection\022" +
      "\024\n\014candidate_id\030\001 \001(\t\022\025\n\rselected_time\030\002" +
      " \001(\t\022\031\n\021selected_location\030\003 \001(\t\"J\n\021Appli" +
      "cationStatus\022\024\n\014candidate_id\030\001 \001(\t\022\016\n\006st" +
      "atus\030\002 \001(\t\022\017\n\007message\030\003 \001(\t\"+\n\026Schedulin" +
      "gConfirmation\022\021\n\tconfirmed\030\001 \001(\010\"9\n\022Noti" +
      "ficationStatus\022\021\n\tdelivered\030\001 \001(\010\022\020\n\010sen" +
      "dtime\030\002 \001(\t2\214\002\n\032CandidateEngagementServi" +
      "ce\022y\n\024ConfirmInterviewSlot\022).CandidateEn" +
      "gagementService.SlotSelection\0322.Candidat" +
      "eEngagementService.SchedulingConfirmatio" +
      "n\"\000(\001\022s\n\020SendStatusUpdate\022-.CandidateEng" +
      "agementService.ApplicationStatus\032..Candi" +
      "dateEngagementService.NotificationStatus" +
      "\"\000BM\n)generated.grpc.candidateengagement" +
      "serviceB\036CandidateEngagementServiceImplP" +
      "\001b\006proto3"
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
        new java.lang.String[] { "CandidateId", "SelectedTime", "SelectedLocation", });
    internal_static_CandidateEngagementService_ApplicationStatus_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_CandidateEngagementService_ApplicationStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CandidateEngagementService_ApplicationStatus_descriptor,
        new java.lang.String[] { "CandidateId", "Status", "Message", });
    internal_static_CandidateEngagementService_SchedulingConfirmation_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_CandidateEngagementService_SchedulingConfirmation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CandidateEngagementService_SchedulingConfirmation_descriptor,
        new java.lang.String[] { "Confirmed", });
    internal_static_CandidateEngagementService_NotificationStatus_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_CandidateEngagementService_NotificationStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CandidateEngagementService_NotificationStatus_descriptor,
        new java.lang.String[] { "Delivered", "Sendtime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
