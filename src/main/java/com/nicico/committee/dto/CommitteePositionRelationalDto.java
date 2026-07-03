package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class CommitteePositionRelationalDto {

    @Builder
    @Schema(name = "CommitteePositionRelationalIdDTO")
    public static record CommitteePositionIdDTO(String id) {
    }

    @Builder
    @Schema(name = "CommitteePositionRelationalInfoDTO", description = "اطلاعات سمت کمیته")
    public static record CommitteePositionInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه کمیته") String committeeId,
            @Schema(description = "کمیته") CommitteeRelationalDto.CommitteeInfoDTO committee,
            @Schema(description = "شناسه سمت") String positionId,
            @Schema(description = "سمت") BaseInfoRelationalDto.BaseInfoInfoDTO position,
            @Schema(description = "آیا چارت نیکو است؟") Boolean isNicicoChart,
            @Schema(description = "تعداد اعضا") Integer numMember,
            @Schema(description = "می تواند رای دهد؟") Boolean canVote,
            @Schema(description = "می تواند امضا کند؟") Boolean canSignature,
            @Schema(description = "می تواند جایگزین شود؟") Boolean canAlternate,
            @Schema(description = "می تواند اسناد را ببیند؟") String canSeeDoc,
            @Schema(description = "شناسه نوع حضور") String presenceTypeId,
            @Schema(description = "نوع حضور") BaseInfoRelationalDto.BaseInfoInfoDTO presenceType,
            @Schema(description = "شناسه شرط حضور") String attendanceConditionId,
            @Schema(description = "شرط حضور") BaseInfoRelationalDto.BaseInfoInfoDTO attendanceCondition,
            @Schema(description = "فعال") Boolean isActive
    ) {
    }

    @Builder
    @Schema(name = "CommitteePositionRelationalCreateDTO")
    public static record CommitteePositionCreateDTO(
            CommitteeRelationalDto.CommitteeIdDTO committee,
            BaseInfoRelationalDto.BaseInfoIdDTO position,
            Boolean isNicicoChart,
            Integer numMember,
            Boolean canVote,
            Boolean canSignature,
            Boolean canAlternate,
            String canSeeDoc,
            BaseInfoRelationalDto.BaseInfoIdDTO presenceType,
            BaseInfoRelationalDto.BaseInfoIdDTO attendanceCondition,
            Boolean isActive
    ) {
    }

    @Builder
    @Schema(name = "CommitteePositionRelationalUpdateDTO")
    public static record CommitteePositionUpdateDTO(
            CommitteeRelationalDto.CommitteeIdDTO committee,
            BaseInfoRelationalDto.BaseInfoIdDTO position,
            Boolean isNicicoChart,
            Integer numMember,
            Boolean canVote,
            Boolean canSignature,
            Boolean canAlternate,
            String canSeeDoc,
            BaseInfoRelationalDto.BaseInfoIdDTO presenceType,
            BaseInfoRelationalDto.BaseInfoIdDTO attendanceCondition,
            Boolean isActive
    ) {
    }
}
