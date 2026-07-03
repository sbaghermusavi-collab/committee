package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.Date;

@Schema(name = "CommitteeCalendarRelationalDto")
public class CommitteeCalendarRelationalDto {

    @Builder
    @Schema(name = "CommitteeCalendarRelationalIdDTO")
    public static record CommitteeCalendarIdDTO(String id) {
    }

    @Builder
    @Schema(name = "CommitteeCalendarRelationalInfoDTO", description = "اطلاعات تقویم کمیته")
    public static record CommitteeCalendarInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه کمیته") String committeeId,
            @Schema(description = "کمیته") CommitteeRelationalDto.CommitteeInfoDTO committee,
            @Schema(description = "شناسه تناوب") String periodicityId,
            @Schema(description = "تناوب") BaseInfoRelationalDto.BaseInfoInfoDTO periodicity,
            @Schema(description = "شناسه مدل جلسه") String meetingModelId,
            @Schema(description = "مدل جلسه") BaseInfoRelationalDto.BaseInfoInfoDTO meetingModel,
            @Schema(description = "تاریخ شروع کمیته") Date startDateCommittee,
            @Schema(description = "تاریخ پایان کمیته") Date endDateCommittee,
            @Schema(description = "تاریخ اولین جلسه") Date firstDateMeeting,
            @Schema(description = "تاریخ جلسه برنامه بعدی") Date nextPlanDateMeeting,
            @Schema(description = "روزهای شناور") Integer floatingDays,
            @Schema(description = "شناسه سمت مسئول کمیته") String responsibleCommitteePositionId,
            @Schema(description = "سمت مسئول کمیته") CommitteePositionRelationalDto.CommitteePositionInfoDTO responsibleCommitteePosition,
            @Schema(description = "فعال") Boolean isActive,
            @Schema(description = "تنظیمات تناوب") CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO periodicitySettings
    ) {
    }

    @Builder
    @Schema(name = "CommitteeCalendarRelationalCreateDTO")
    public static record CommitteeCalendarCreateDTO(
            CommitteeRelationalDto.CommitteeIdDTO committee,
            BaseInfoRelationalDto.BaseInfoIdDTO periodicity,
            BaseInfoRelationalDto.BaseInfoIdDTO meetingModel,
            Date startDateCommittee,
            Date endDateCommittee,
            Date firstDateMeeting,
            Date nextPlanDateMeeting,
            Integer floatingDays,
            CommitteePositionRelationalDto.CommitteePositionIdDTO responsibleCommitteePosition,
            Boolean isActive,
            CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsIdDTO periodicitySettings
    ) {
    }

    @Builder
    @Schema(name = "CommitteeCalendarRelationalUpdateDTO")
    public static record CommitteeCalendarUpdateDTO(
            CommitteeRelationalDto.CommitteeIdDTO committee,
            BaseInfoRelationalDto.BaseInfoIdDTO periodicity,
            BaseInfoRelationalDto.BaseInfoIdDTO meetingModel,
            Date startDateCommittee,
            Date endDateCommittee,
            Date firstDateMeeting,
            Date nextPlanDateMeeting,
            Integer floatingDays,
            CommitteePositionRelationalDto.CommitteePositionIdDTO responsibleCommitteePosition,
            Boolean isActive,
            CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsIdDTO periodicitySettings
    ) {
    }
}
