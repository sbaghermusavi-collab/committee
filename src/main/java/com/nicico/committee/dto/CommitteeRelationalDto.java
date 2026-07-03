package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class CommitteeRelationalDto {

    @Builder
    @Schema(name = "CommitteeRelationalIdDTO")
    public static record CommitteeIdDTO(String id) {}

    @Builder
    @Schema(name = "CommitteeRelationalInfoDTO", description = "اطلاعات کمیسیون")
    public static record CommitteeInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه ماهیت سازمانی") String categoryId,
            @Schema(description = "ماهیت سازمانی") BaseInfoRelationalDto.BaseInfoInfoDTO category,
            @Schema(description = "شناسه حوزه کسب و کار") String businessDomainId,
            @Schema(description = "حوزه کسب و کار") BaseInfoRelationalDto.BaseInfoInfoDTO businessDomain,
            @Schema(description = "شناسه حوزه موضوعی") String subjectCategoryId,
            @Schema(description = "حوزه موضوعی") BaseInfoRelationalDto.BaseInfoInfoDTO subjectCategory,
            @Schema(description = "شناسه کمیسیون والد") String parentCommitteeId,
            @Schema(description = "کمیسیون والد") CommitteeInfoDTO parentCommittee,
            @Schema(description = "شناسه مالک سیستم") String systemOwnerId,
            @Schema(description = "مالک سیستم") BaseInfoRelationalDto.BaseInfoInfoDTO systemOwner,
            @Schema(description = "آیا از سازمان داخلی است؟") Boolean isNicico,
            @Schema(description = "شناسه سازمان متولی") String committeeOwnerId,
            @Schema(description = "عنوان متولی") String committeeOwnerTitel,
            @Schema(description = "شناسه منطقه برگزاری") String committeeLocationId,
            @Schema(description = "منطقه برگزاری") BaseInfoRelationalDto.BaseInfoInfoDTO committeeLocation,
            @Schema(description = "آدرس") String committeeAddress,
            @Schema(description = "ساعت برگزاری") String committeeTime,
            @Schema(description = "کد اختصاری") String code,
            @Schema(description = "نام") String name,
            @Schema(description = "توضیحات") String description,
            @Schema(description = "شناسه سطح کمیسیون") String committeeLevelId,
            @Schema(description = "سطح کمیسیون") BaseInfoRelationalDto.BaseInfoInfoDTO committeeLevel,
            @Schema(description = "مستندات") String documentIds,
            @Schema(description = "حداقل اعضاء") Integer minMembersCommittee,
            @Schema(description = "قابلیت تمدید") Boolean isExtendDate,
            @Schema(description = "فعال") Boolean isActive
    ) {}

    @Builder
    @Schema(name = "CommitteeRelationalCreateDTO")
    public static record CommitteeCreateDTO(
            BaseInfoRelationalDto.BaseInfoIdDTO category,
            BaseInfoRelationalDto.BaseInfoIdDTO businessDomain,
            BaseInfoRelationalDto.BaseInfoIdDTO subjectCategory,
            CommitteeIdDTO parentCommittee,
            BaseInfoRelationalDto.BaseInfoIdDTO systemOwner,
            Boolean isNicico,
            String committeeOwnerId,
            String committeeOwnerTitel,
            BaseInfoRelationalDto.BaseInfoIdDTO committeeLocation,
            String committeeAddress,
            String committeeTime,
            String code,
            String name,
            String description,
            BaseInfoRelationalDto.BaseInfoIdDTO committeeLevel,
            String documentIds,
            Integer minMembersCommittee,
            Boolean isExtendDate,
            Boolean isActive
    ) {}

    @Builder
    @Schema(name = "CommitteeRelationalUpdateDTO")
    public static record CommitteeUpdateDTO(
            BaseInfoRelationalDto.BaseInfoIdDTO category,
            BaseInfoRelationalDto.BaseInfoIdDTO businessDomain,
            BaseInfoRelationalDto.BaseInfoIdDTO subjectCategory,
            CommitteeIdDTO parentCommittee,
            BaseInfoRelationalDto.BaseInfoIdDTO systemOwner,
            Boolean isNicico,
            String committeeOwnerId,
            String committeeOwnerTitel,
            BaseInfoRelationalDto.BaseInfoIdDTO committeeLocation,
            String committeeAddress,
            String committeeTime,
            String code,
            String name,
            String description,
            BaseInfoRelationalDto.BaseInfoIdDTO committeeLevel,
            String documentIds,
            Integer minMembersCommittee,
            Boolean isExtendDate,
            Boolean isActive
    ) {}
}