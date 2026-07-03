package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.LocalDate;

public class MeetingDecisionRelationalDto {

    @Builder
    @Schema(name = "MeetingDecisionRelationalIdDTO")
    public static record MeetingDecisionIdDTO(String id) {}

    @Builder
    @Schema(name = "MeetingDecisionRelationalInfoDTO", description = "تصمیمات جلسه")
    public static record MeetingDecisionInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه جلسه") String meetingId,
            @Schema(description = "جلسه") MeetingRelationalDto.MeetingInfoDTO meeting,
            @Schema(description = "شناسه دستور کار") String agendaItemId,
            @Schema(description = "دستور کار") MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO agendaItem,
            @Schema(description = "موضوع") String subject,
            @Schema(description = "شرح") String description,
            @Schema(description = "پیشنهاد دهندگان") String suggesterIds,
            @Schema(description = "روش اخذ تصمیم") String votingMethod,
            @Schema(description = "شناسه نوع تصمیم") String decisionTypeId,
            @Schema(description = "نوع تصمیم") BaseInfoRelationalDto.BaseInfoInfoDTO decisionType,
            @Schema(description = "ارجاع به جلسه") String referralMeetingId,
            @Schema(description = "تاریخ تصمیم") LocalDate decisionDate,
            @Schema(description = "مهلت") LocalDate dueDate,
            @Schema(description = "فعال") Boolean isActive
    ) {}

    @Builder
    @Schema(name = "MeetingDecisionRelationalCreateDTO")
    public static record MeetingDecisionCreateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            MeetingAgendaItemRelationalDto.MeetingAgendaItemIdDTO agendaItem,
            String subject,
            String description,
            String suggesterIds,
            String votingMethod,
            BaseInfoRelationalDto.BaseInfoIdDTO decisionType,
            MeetingRelationalDto.MeetingIdDTO referralMeeting,
            LocalDate decisionDate,
            LocalDate dueDate,
            Boolean isActive
    ) {}

    @Builder
    @Schema(name = "MeetingDecisionRelationalUpdateDTO")
    public static record MeetingDecisionUpdateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            MeetingAgendaItemRelationalDto.MeetingAgendaItemIdDTO agendaItem,
            String subject,
            String description,
            String suggesterIds,
            String votingMethod,
            BaseInfoRelationalDto.BaseInfoIdDTO decisionType,
            MeetingRelationalDto.MeetingIdDTO referralMeeting,
            LocalDate decisionDate,
            LocalDate dueDate,
            Boolean isActive
    ) {}
}