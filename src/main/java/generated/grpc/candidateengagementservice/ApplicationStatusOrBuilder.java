// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CandidateEngagementService.proto

package generated.grpc.candidateengagementservice;

public interface ApplicationStatusOrBuilder extends
    // @@protoc_insertion_point(interface_extends:CandidateEngagementService.ApplicationStatus)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string candidate_id = 1;</code>
   */
  java.lang.String getCandidateId();
  /**
   * <code>string candidate_id = 1;</code>
   */
  com.google.protobuf.ByteString
      getCandidateIdBytes();

  /**
   * <pre>
   * e.g., "In Review", "Selected for Interview", "Rejected", "Offer Extended"
   * </pre>
   *
   * <code>string status = 2;</code>
   */
  java.lang.String getStatus();
  /**
   * <pre>
   * e.g., "In Review", "Selected for Interview", "Rejected", "Offer Extended"
   * </pre>
   *
   * <code>string status = 2;</code>
   */
  com.google.protobuf.ByteString
      getStatusBytes();

  /**
   * <pre>
   * Additional details
   * </pre>
   *
   * <code>string message = 3;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   * Additional details
   * </pre>
   *
   * <code>string message = 3;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
