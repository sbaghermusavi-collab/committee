package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.LocalDate;

public class MeetingItemInvitationRelationalDto {

    @Builder
    @Schema(name = "MeetingItemInvitationRelationalIdDTO")
    public static record MeetingItemInvitationIdDTO(String id) {}

    @Builder
    @Schema(name = "MeetingItemInvitationRelationalInfoDTO", description = "اطلاعات دعوت به جلسه")
    public static record MeetingItemInvitationInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه جلسه") String meetingId,
            @Schema(description = "جلسه") MeetingRelationalDto.MeetingInfoDTO meeting,
            @Schema(description = "شناسه دستور کار") String agendaItemId,
            @Schema(description = "دستور کار") MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO agendaItem,
            @Schema(description = "سازمانی") Boolean isNicico,
            @Schema(description = "شناسه سمت") String positionId,
            @Schema(description = "سمت") BaseInfoRelationalDto.BaseInfoInfoDTO position,
            @Schema(description = "سمت سازمانی") String nicicoPositionId,
            @Schema(description = "کد ملی") String nationalCode,
            @Schema(description = "حضور الزامی") Boolean isRequired,
            @Schema(description = "حق رأی") Boolean canVote,
            @Schema(description = "حق امضا") Boolean canSignature,
            @Schema(description = "مشاهده اسناد") String canSeeDoc,
            @Schema(description = "ایمیل") String email,
            @Schema(description = "تلفن") String phone,
            @Schema(description = "شناسه شرکت") String companyId,
            @Schema(description = "سمت مدعو") String guestPosition,
            @Schema(description = "وضعیت دعوت") String invitationStatus,
            @Schema(description = "وضعیت حضور") String attendanceStatus,
            @Schema(description = "تاریخ ارسال دعوت") LocalDate invitationSentDate,
            @Schema(description = "تاریخ پاسخ") LocalDate responseDate,
            @Schema(description = "مستندات") String documentIds,
            @Schema(description = "اسناد مرتبط") String documentLinkIds,
            @Schema(description = "توضیحات") String notes
    ) {}

    @Builder
    @Schema(name = "MeetingItemInvitationRelationalCreateDTO")
    public static record MeetingItemInvitationCreateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            MeetingAgendaItemRelationalDto.MeetingAgendaItemIdDTO agendaItem,
            Boolean isNicico,
            BaseInfoRelationalDto.BaseInfoIdDTO position,
            String nicicoPositionId,
            String nationalCode,
            Boolean isRequired,
            Boolean canVote,
            Boolean canSignature,
            String canSeeDoc,
            String email,
            String phone,
            String companyId,
            String guestPosition,
            String invitationStatus,
            String attendanceStatus,
            LocalDate invitationSentDate,
            LocalDate responseDate,
            String documentIds,
            String documentLinkIds,
            String notes
    ) {}

    @Builder
    @Schema(name = "MeetingItemInvitationRelationalUpdateDTO")
    public static record MeetingItemInvitationUpdateDTO(
            MeetingRelationalDto.MeetingIdDTO meeting,
            MeetingAgendaItemRelationalDto.MeetingAgendaItemIdDTO agendaItem,
            Boolean isNicico,
            BaseInfoRelationalDto.BaseInfoIdDTO position,
            String nicicoPositionId,
            String nationalCode,
            Boolean isRequired,
            Boolean canVote,
            Boolean canSignature,
            String canSeeDoc,
            String email,
            String phone,
            String companyId,
            String guestPosition,
            String invitationStatus,
            String attendanceStatus,
            LocalDate invitationSentDate,
            LocalDate responseDate,
            String documentIds,
            String documentLinkIds,
            String notes
    ) {}
}