package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.Date;

public class CommitteeMemberAssignmentRelationalDto {

    @Builder
    @Schema(name = "CommitteeMemberAssignmentRelationalIdDTO")
    public static record CommitteeMemberAssignmentIdDTO(String id) {
    }

    @Builder
    @Schema(name = "CommitteeMemberAssignmentRelationalInfoDTO", description = "اطلاعات تخصیص عضو کمیته")
    public static record CommitteeMemberAssignmentInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه سمت کمیته") String committeePositionId,
            @Schema(description = "سمت کمیته") CommitteePositionRelationalDto.CommitteePositionInfoDTO committeePosition,
            @Schema(description = "شناسه سمت نیکو") String nicicoPositionId,
            @Schema(description = "کد ملی") String nationalCode,
            @Schema(description = "عضو جایگزین") Boolean alternateMember,
            @Schema(description = "شناسه سمت جایگزین نیکو") String altNicicoPositionId,
            @Schema(description = "کد ملی عضو جایگزین") String altMemberNationalCode,
            @Schema(description = "عنوان غیر نیکو") String nonicicoTitel,
            @Schema(description = "تاریخ شروع") Date startDate,
            @Schema(description = "تاریخ پایان") Date endDate,
            @Schema(description = "آیا تاریخ تمدید شده است؟") Boolean isExtendDate,
            @Schema(description = "فعال") Boolean isActive,
            @Schema(description = "شناسه اسناد") String documentIds
    ) {
    }

    @Builder
    @Schema(name = "CommitteeMemberAssignmentRelationalCreateDTO")
    public static record CommitteeMemberAssignmentCreateDTO(
            CommitteePositionRelationalDto.CommitteePositionIdDTO committeePosition,
            String nicicoPositionId,
            String nationalCode,
            Boolean alternateMember,
            String altNicicoPositionId,
            String altMemberNationalCode,
            String nonicicoTitel,
            Date startDate,
            Date endDate,
            Boolean isExtendDate,
            Boolean isActive,
            String documentIds
    ) {
    }

    @Builder
    @Schema(name = "CommitteeMemberAssignmentRelationalUpdateDTO")
    public static record CommitteeMemberAssignmentUpdateDTO(
            CommitteePositionRelationalDto.CommitteePositionIdDTO committeePosition,
            String nicicoPositionId,
            String nationalCode,
            Boolean alternateMember,
            String altNicicoPositionId,
            String altMemberNationalCode,
            String nonicicoTitel,
            Date startDate,
            Date endDate,
            Boolean isExtendDate,
            Boolean isActive,
            String documentIds
    ) {
    }
}
