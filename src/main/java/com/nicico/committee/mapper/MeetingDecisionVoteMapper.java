package com.nicico.committee.mapper;

import com.nicico.committee.dto.MeetingDecisionVoteRelationalDto;
import com.nicico.committee.entities.MeetingDecisionVote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MeetingDecisionMapper.class})
public interface MeetingDecisionVoteMapper extends DefaultMapper<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO,
        MeetingDecisionVoteRelationalDto.MeetingDecisionVoteUpdateDTO, MeetingDecisionVoteRelationalDto.MeetingDecisionVoteCreateDTO, MeetingDecisionVote> {

    @Override
    MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO toDto(MeetingDecisionVote entity);

    @Named("toIdDto")
    MeetingDecisionVoteRelationalDto.MeetingDecisionVoteIdDTO toIdDto(MeetingDecisionVote entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "decisionId", source = "decision.id")
    @Mapping(target = "decision", ignore = true)
    @Mapping(target = "hiddenVote", ignore = true)
    MeetingDecisionVote toEntityForCreate(MeetingDecisionVoteRelationalDto.MeetingDecisionVoteCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "decisionId", source = "decision.id")
    @Mapping(target = "decision", ignore = true)
    @Mapping(target = "hiddenVote", ignore = true)
    MeetingDecisionVote toEntityForUpdate(MeetingDecisionVoteRelationalDto.MeetingDecisionVoteUpdateDTO updateDto);
}