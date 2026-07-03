package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class BaseInfoRelationalDto {

    @Builder
    @Schema(name = "BaseInfoRelationalIdDTO")
    public static record BaseInfoIdDTO(String id) {
    }

    @Builder
    @Schema(name = "BaseInfoRelationalInfoDTO", description = "اطلاعات پایه")
    public static record BaseInfoInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "نام فارسی") String persian,
            @Schema(description = "نام لاتین") String latin,
            @Schema(description = "کد") String code,
            @Schema(description = "والد") BaseInfoInfoBriefDTO parent,
            @Schema(description = "شناسه والد") String parentId,
            @Schema(description = "فعال") Boolean active,
            @Schema(description = "پیش فرض") Boolean isDefault
    ) {
    }

    @Builder
    @Schema(name = "BaseInfoRelationalCreateDTO")
    public static record BaseInfoCreateDTO(
            String persian,
            String latin,
            String code,
            BaseInfoIdDTO parent,
            Boolean active,
            Boolean isDefault
    ) {
    }

    @Builder
    @Schema(name = "BaseInfoRelationalUpdateDTO")
    public static record BaseInfoUpdateDTO(
            String persian,
            String latin,
            String code,
            BaseInfoIdDTO parent,
            Boolean active,
            Boolean isDefault
    ) {
    }



    @Builder
    @Schema(name = "BaseInfoRelationalInfoDTO", description = "اطلاعات پایه")
    public static record BaseInfoInfoBriefDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "نام فارسی") String persian,
            @Schema(description = "نام لاتین") String latin,
            @Schema(description = "کد") String code
    ) {
    }
}
