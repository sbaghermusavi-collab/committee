package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class MeetingAttendanceRelationalDto {

    @Builder
    @Schema(name = "MeetingAttendanceRelationalIdDTO")
    public static record MeetingAttendanceIdDTO(String id) {}

    @Builder
    @Schema(name = "MeetingAttendanceRelationalInfoDTO", description = "اطلاعات حضور در جلسه")
    public static record MeetingAttendanceInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه جلسه") String meetingId,
            @Schema(description = "جلسه") MeetingRelationalDto.MeetingInfoDTO meeting,
            @Schema(description = "شناسه انتصاب") String assignmentId,
            @Schema(description = "انتصاب") CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO assignment,
            @Schema(description = "وضعیت حضور") String attendanceStatus,
            @Schema(description = "شناسه جانشین") String delegateMemberId,
            @Schema(description = "وضعیت جانشین") String delegateStatus,
            @Schema(description = "امضا شده") Boolean signedMinutes,
            @Schema(description = "شناسه سمت") String positionId,
            @Schema(description = "سمت") BaseInfoRelationalDto.BaseInfoInfoDTO position,
            @Schema(description = "سازمانی") Boolean isNicico,
            @Schema(description = "حق رأی") Boolean canVote,
            @Schema(description = "حق امضا") Boolean canSignature,
            @Schema(description = "مشاهده اسناد") String canSeeDoc,
            @Schema(description = "شناسه نوع حضور") String presenceTypeId,
            @Schema(description = "نوع حضور") BaseInfoRelationalDto.BaseInfoInfoDTO presenceType,
            @Schema(description = "شناسه شرط حضور") String attendanceConditionId,
            @Schema(description = "شرط حضور") BaseInfoRelationalDto.BaseInfoInfoDTO attendanceCondition,
            @Schema(description = "سمت سازمانی") String nicicoPositionId,
            @Schema(description = "کد ملی") String nationalCode,
            @Schema(description = "علی‌البدل") Boolean alternateMember,
            @Schema(description = "سمت سازمانی جانشین") String altNicicoPositionId,
            @Schema(description = "کد ملی جانشین") String altMemberNationalCode,
            @Schema(description = "عنوان غیرسازمانی") String nonicicoTitel,
            @Schema(description = "سند امضا") String signedAttendanceDocumentId,
            @Schema(description = "مستندات") String documentIds,
            @Schema(description = "اسناد مرتبط") String documentLinkIds
    ) {}

    @Builder
    @Schema(name = "MeetingAttendanceRelationalCreateDTO")
    public static record MeetingAttendanceCreateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentIdDTO assignment,
            String attendanceStatus,
            String delegateMemberId,
            String delegateStatus,
            Boolean signedMinutes,
            BaseInfoRelationalDto.BaseInfoIdDTO position,
            Boolean isNicico,
            Boolean canVote,
            Boolean canSignature,
            String canSeeDoc,
            BaseInfoRelationalDto.BaseInfoIdDTO presenceType,
            BaseInfoRelationalDto.BaseInfoIdDTO attendanceCondition,
            String nicicoPositionId,
            String nationalCode,
            Boolean alternateMember,
            String altNicicoPositionId,
            String altMemberNationalCode,
            String nonicicoTitel,
            String signedAttendanceDocumentId,
            String documentIds,
            String documentLinkIds
    ) {}

    @Builder
    @Schema(name = "MeetingAttendanceRelationalUpdateDTO")
    public static record MeetingAttendanceUpdateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentIdDTO assignment,
            String attendanceStatus,
            String delegateMemberId,
            String delegateStatus,
            Boolean signedMinutes,
            BaseInfoRelationalDto.BaseInfoIdDTO position,
            Boolean isNicico,
            Boolean canVote,
            Boolean canSignature,
            String canSeeDoc,
            BaseInfoRelationalDto.BaseInfoIdDTO presenceType,
            BaseInfoRelationalDto.BaseInfoIdDTO attendanceCondition,
            String nicicoPositionId,
            String nationalCode,
            Boolean alternateMember,
            String altNicicoPositionId,
            String altMemberNationalCode,
            String nonicicoTitel,
            String signedAttendanceDocumentId,
            String documentIds,
            String documentLinkIds
    ) {}
}