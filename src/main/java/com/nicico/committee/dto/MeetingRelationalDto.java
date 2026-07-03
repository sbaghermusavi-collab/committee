package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.LocalDate;

public class MeetingRelationalDto {

    @Builder
    @Schema(name = "MeetingRelationalIdDTO")
    public static record MeetingIdDTO(String id) {}

    @Builder
    @Schema(name = "MeetingRelationalInfoDTO", description = "اطلاعات جلسه")
    public static record MeetingInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه کمیسیون") String commissionId,
            @Schema(description = "کمیسیون") CommitteeRelationalDto.CommitteeInfoDTO commission,
            @Schema(description = "کد کلاسه") String code,
            @Schema(description = "شماره جلسه") String meetingNumber,
            @Schema(description = "تاریخ جلسه") LocalDate meetingDate,
            @Schema(description = "زمان شروع") String meetingStartTime,
            @Schema(description = "زمان پایان") String meetingEndTime,
            @Schema(description = "شناسه منطقه") String locationId,
            @Schema(description = "منطقه برگزاری") BaseInfoRelationalDto.BaseInfoInfoDTO location,
            @Schema(description = "آدرس") String address,
            @Schema(description = "لینک مجازی") String meetingLink,
            @Schema(description = "دستور جلسه") String agenda,
            @Schema(description = "شناسه نوع جلسه") String meetingNatureId,
            @Schema(description = "نوع جلسه") BaseInfoRelationalDto.BaseInfoInfoDTO meetingNature,
            @Schema(description = "شناسه وضعیت") String statusId,
            @Schema(description = "وضعیت") BaseInfoRelationalDto.BaseInfoInfoDTO status,
            @Schema(description = "مستندات") String documentIds,
            @Schema(description = "مستندات مرتبط") String documentLinkIds
    ) {}

    @Builder
    @Schema(name = "MeetingRelationalCreateDTO")
    public static record MeetingCreateDTO(
            CommitteeRelationalDto.CommitteeIdDTO commission,
            String code,
            String meetingNumber,
            LocalDate meetingDate,
            String meetingStartTime,
            String meetingEndTime,
            BaseInfoRelationalDto.BaseInfoIdDTO location,
            String address,
            String meetingLink,
            String agenda,
            BaseInfoRelationalDto.BaseInfoIdDTO meetingNature,
            BaseInfoRelationalDto.BaseInfoIdDTO status,
            String documentIds,
            String documentLinkIds
    ) {}

    @Builder
    @Schema(name = "MeetingRelationalUpdateDTO")
    public static record MeetingUpdateDTO(
            CommitteeRelationalDto.CommitteeIdDTO commission,
            String code,
            String meetingNumber,
            LocalDate meetingDate,
            String meetingStartTime,
            String meetingEndTime,
            BaseInfoRelationalDto.BaseInfoIdDTO location,
            String address,
            String meetingLink,
            String agenda,
            BaseInfoRelationalDto.BaseInfoIdDTO meetingNature,
            BaseInfoRelationalDto.BaseInfoIdDTO status,
            String documentIds,
            String documentLinkIds
    ) {}
}