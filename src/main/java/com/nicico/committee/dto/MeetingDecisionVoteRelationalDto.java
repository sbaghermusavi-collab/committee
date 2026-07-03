package com.nicico.committee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class MeetingDecisionVoteRelationalDto {

    @Builder
    @Schema(name = "MeetingDecisionVoteRelationalIdDTO")
    public static record MeetingDecisionVoteIdDTO(String id) {}

    @Builder
    @Schema(name = "MeetingDecisionVoteRelationalInfoDTO", description = "آراء تصمیمات")
    public static record MeetingDecisionVoteInfoDTO(
            @Schema(description = "شناسه") String id,
            @Schema(description = "شناسه تصمیم") String decisionId,
            @Schema(description = "تصمیم") MeetingDecisionRelationalDto.MeetingDecisionInfoDTO decision,
            @Schema(description = "شناسه عضو") String memberId,
            @Schema(description = "کد ملی") String nationalCode,
            @Schema(description = "نوع رأی") String voteType,
            @Schema(description = "شرح شرط") String conditionalDescription,
            @Schema(description = "رأی مخفی") Boolean hiddenVote
    ) {}

    @Builder
    @Schema(name = "MeetingDecisionVoteRelationalCreateDTO")
    public static record MeetingDecisionVoteCreateDTO(
            MeetingDecisionRelationalDto.MeetingDecisionIdDTO decision,
            String memberId,
            String nationalCode,
            String voteType,
            String conditionalDescription,
            Boolean hiddenVote
    ) {}

    @Builder
    @Schema(name = "MeetingDecisionVoteRelationalUpdateDTO")
    public static record MeetingDecisionVoteUpdateDTO(
            MeetingDecisionRelationalDto.MeetingDecisionIdDTO decision,
            String memberId,
            String nationalCode,
            String voteType,
            String conditionalDescription,
            Boolean hiddenVote
    ) {}
}