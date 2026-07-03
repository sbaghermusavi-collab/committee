package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class CommitteePeriodicitySettingsRelationalDto {

    @Builder
    @Schema(name = "CommitteePeriodicitySettingsRelationalIdDTO")
    public static record CommitteePeriodicitySettingsIdDTO(String id) {
    }

    @Builder
    @Schema(name = "CommitteePeriodicitySettingsRelationalInfoDTO", description = "اطلاعات تنظیمات تناوب کمیته")
    public static record CommitteePeriodicitySettingsInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه تقویم") String calendarId,
            @Schema(description = "تقویم") CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO calendar,
            @Schema(description = "مقدار فاصله") Integer intervalValue,
            @Schema(description = "ماسک روز هفته") String weekdayMask,
            @Schema(description = "روز ماه") String dayOfMonth,
            @Schema(description = "هفته ماه") String weekOfMonth,
            @Schema(description = "ماسک ماه") String monthMask,
            @Schema(description = "قانون تعطیلات") String holidayRule
    ) {
    }

    @Builder
    @Schema(name = "CommitteePeriodicitySettingsRelationalCreateDTO")
    public static record CommitteePeriodicitySettingsCreateDTO(
            CommitteeCalendarRelationalDto.CommitteeCalendarIdDTO calendar,
            Integer intervalValue,
            String weekdayMask,
            String dayOfMonth,
            String weekOfMonth,
            String monthMask,
            String holidayRule
    ) {
    }

    @Builder
    @Schema(name = "CommitteePeriodicitySettingsRelationalUpdateDTO")
    public static record CommitteePeriodicitySettingsUpdateDTO(
            CommitteeCalendarRelationalDto.CommitteeCalendarIdDTO calendar,
            Integer intervalValue,
            String weekdayMask,
            String dayOfMonth,
            String weekOfMonth,
            String monthMask,
            String holidayRule
    ) {
    }
}
