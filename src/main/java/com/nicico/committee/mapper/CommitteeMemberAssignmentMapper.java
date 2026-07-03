package com.nicico.committee.mapper;

import com.nicico.committee.dto.CommitteeMemberAssignmentRelationalDto;
import com.nicico.committee.entities.CommitteeMemberAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CommitteePositionMapper.class)
public interface CommitteeMemberAssignmentMapper extends DefaultMapper<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO,
        CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentUpdateDTO, CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentCreateDTO, CommitteeMemberAssignment> {

    @Override
    CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO toDto(CommitteeMemberAssignment entity);

    @Named("toIdDto")
    CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentIdDTO toIdDto(CommitteeMemberAssignment entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "committeePositionId", source = "committeePosition.id")
    @Mapping(target = "committeePosition", ignore = true)
    CommitteeMemberAssignment toEntityForCreate(CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "committeePositionId", source = "committeePosition.id")
    @Mapping(target = "committeePosition", ignore = true)
    CommitteeMemberAssignment toEntityForUpdate(CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentUpdateDTO updateDto);
}
