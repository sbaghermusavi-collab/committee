package com.nicico.committee.mapper;

import com.nicico.committee.dto.MeetingAgendaItemRelationalDto;
import com.nicico.committee.entities.MeetingAgendaItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MeetingMapper.class, BaseInfoMapper.class})
public interface MeetingAgendaItemMapper extends DefaultMapper<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO,
        MeetingAgendaItemRelationalDto.MeetingAgendaItemUpdateDTO, MeetingAgendaItemRelationalDto.MeetingAgendaItemCreateDTO, MeetingAgendaItem> {

    @Override
    MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO toDto(MeetingAgendaItem entity);

    @Named("toIdDto")
    MeetingAgendaItemRelationalDto.MeetingAgendaItemIdDTO toIdDto(MeetingAgendaItem entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "agendaDecisionTypeId", source = "agendaDecisionType.id")
    @Mapping(target = "agendaItemStatusId", source = "agendaItemStatus.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "agendaDecisionType", ignore = true)
    @Mapping(target = "agendaItemStatus", ignore = true)
    MeetingAgendaItem toEntityForCreate(MeetingAgendaItemRelationalDto.MeetingAgendaItemCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "agendaDecisionTypeId", source = "agendaDecisionType.id")
    @Mapping(target = "agendaItemStatusId", source = "agendaItemStatus.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "agendaDecisionType", ignore = true)
    @Mapping(target = "agendaItemStatus", ignore = true)
    MeetingAgendaItem toEntityForUpdate(MeetingAgendaItemRelationalDto.MeetingAgendaItemUpdateDTO updateDto);
}