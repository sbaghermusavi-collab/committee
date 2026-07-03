package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class MeetingQuorumRelationalDto {

    @Builder
    @Schema(name = "MeetingQuorumRelationalIdDTO")
    public static record MeetingQuorumIdDTO(String id) {}

    @Builder
    @Schema(name = "MeetingQuorumRelationalInfoDTO", description = "حد نصاب جلسه")
    public static record MeetingQuorumInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه جلسه") String meetingId,
            @Schema(description = "جلسه") MeetingRelationalDto.MeetingInfoDTO meeting,
            @Schema(description = "تعداد الزامی") Integer requiredMembers,
            @Schema(description = "تعداد حاضر") Integer actualPresent,
            @Schema(description = "رسمیت") Boolean isValid,
            @Schema(description = "دلایل عدم برگزاری") String meetingInvalidReasonIds,
            @Schema(description = "توضیح") String quorumNote
    ) {}

    @Builder
    @Schema(name = "MeetingQuorumRelationalCreateDTO")
    public static record MeetingQuorumCreateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            Integer requiredMembers,
            Integer actualPresent,
            Boolean isValid,
            String meetingInvalidReasonIds,
            String quorumNote
    ) {}

    @Builder
    @Schema(name = "MeetingQuorumRelationalUpdateDTO")
    public static record MeetingQuorumUpdateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            Integer requiredMembers,
            Integer actualPresent,
            Boolean isValid,
            String meetingInvalidReasonIds,
            String quorumNote
    ) {}
}