package com.nicico.committee.mapper;

import com.nicico.committee.dto.MeetingQuorumRelationalDto;
import com.nicico.committee.entities.MeetingQuorum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MeetingMapper.class})
public interface MeetingQuorumMapper extends DefaultMapper<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO,
        MeetingQuorumRelationalDto.MeetingQuorumUpdateDTO, MeetingQuorumRelationalDto.MeetingQuorumCreateDTO, MeetingQuorum> {

    @Override
    MeetingQuorumRelationalDto.MeetingQuorumInfoDTO toDto(MeetingQuorum entity);

    @Named("toIdDto")
    MeetingQuorumRelationalDto.MeetingQuorumIdDTO toIdDto(MeetingQuorum entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "meeting", ignore = true)
    MeetingQuorum toEntityForCreate(MeetingQuorumRelationalDto.MeetingQuorumCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "meeting", ignore = true)
    MeetingQuorum toEntityForUpdate(MeetingQuorumRelationalDto.MeetingQuorumUpdateDTO updateDto);
}