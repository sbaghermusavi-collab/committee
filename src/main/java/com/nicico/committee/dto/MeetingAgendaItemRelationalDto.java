package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class MeetingAgendaItemRelationalDto {

    @Builder
    @Schema(name = "MeetingAgendaItemRelationalIdDTO")
    public static record MeetingAgendaItemIdDTO(String id) {}

    @Builder
    @Schema(name = "MeetingAgendaItemRelationalInfoDTO", description = "اطلاعات دستور کار جلسه")
    public static record MeetingAgendaItemInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه جلسه") String meetingId,
            @Schema(description = "جلسه") MeetingRelationalDto.MeetingInfoDTO meeting,
            @Schema(description = "ردیف") Integer itemOrder,
            @Schema(description = "تعداد دفعات طرح") Integer presentationCount,
            @Schema(description = "عنوان") String title,
            @Schema(description = "توضیحات") String description,
            @Schema(description = "شناسه نوع تصمیم") String agendaDecisionTypeId,
            @Schema(description = "نوع تصمیم") BaseInfoRelationalDto.BaseInfoInfoDTO agendaDecisionType,
            @Schema(description = "تصمیم") String decision,
            @Schema(description = "شناسه وضعیت") String agendaItemStatusId,
            @Schema(description = "وضعیت") BaseInfoRelationalDto.BaseInfoInfoDTO agendaItemStatus
    ) {}

    @Builder
    @Schema(name = "MeetingAgendaItemRelationalCreateDTO")
    public static record MeetingAgendaItemCreateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            Integer itemOrder,
            Integer presentationCount,
            String title,
            String description,
            BaseInfoRelationalDto.BaseInfoIdDTO agendaDecisionType,
            String decision,
            BaseInfoRelationalDto.BaseInfoIdDTO agendaItemStatus
    ) {}

    @Builder
    @Schema(name = "MeetingAgendaItemRelationalUpdateDTO")
    public static record MeetingAgendaItemUpdateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            Integer itemOrder,
            Integer presentationCount,
            String title,
            String description,
            BaseInfoRelationalDto.BaseInfoIdDTO agendaDecisionType,
            String decision,
            BaseInfoRelationalDto.BaseInfoIdDTO agendaItemStatus
    ) {}
}