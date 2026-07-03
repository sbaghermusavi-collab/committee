package com.nicico.committee.mapper;

import com.nicico.committee.dto.MeetingRelationalDto;
import com.nicico.committee.entities.Meeting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommitteeMapper.class, BaseInfoMapper.class})
public interface MeetingMapper extends DefaultMapper<MeetingRelationalDto.MeetingInfoDTO,
        MeetingRelationalDto.MeetingUpdateDTO, MeetingRelationalDto.MeetingCreateDTO, Meeting> {

    @Override
    MeetingRelationalDto.MeetingInfoDTO toDto(Meeting entity);

    @Named("toIdDto")
    MeetingRelationalDto.MeetingIdDTO toIdDto(Meeting entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "commissionId", source = "commission.id")
    @Mapping(target = "locationId", source = "location.id")
    @Mapping(target = "meetingNatureId", source = "meetingNature.id")
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "commission", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "meetingNature", ignore = true)
    @Mapping(target = "status", ignore = true)
    Meeting toEntityForCreate(MeetingRelationalDto.MeetingCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "commissionId", source = "commission.id")
    @Mapping(target = "locationId", source = "location.id")
    @Mapping(target = "meetingNatureId", source = "meetingNature.id")
    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "commission", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "meetingNature", ignore = true)
    @Mapping(target = "status", ignore = true)
    Meeting toEntityForUpdate(MeetingRelationalDto.MeetingUpdateDTO updateDto);
}