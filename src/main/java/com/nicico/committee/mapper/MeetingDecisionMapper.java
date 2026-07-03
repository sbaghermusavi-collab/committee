package com.nicico.committee.mapper;

import com.nicico.committee.dto.MeetingDecisionRelationalDto;
import com.nicico.committee.entities.MeetingDecision;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MeetingMapper.class, MeetingAgendaItemMapper.class, BaseInfoMapper.class})
public interface MeetingDecisionMapper extends DefaultMapper<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO,
        MeetingDecisionRelationalDto.MeetingDecisionUpdateDTO, MeetingDecisionRelationalDto.MeetingDecisionCreateDTO, MeetingDecision> {

    @Override
    MeetingDecisionRelationalDto.MeetingDecisionInfoDTO toDto(MeetingDecision entity);

    @Named("toIdDto")
    MeetingDecisionRelationalDto.MeetingDecisionIdDTO toIdDto(MeetingDecision entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "agendaItemId", source = "agendaItem.id")
    @Mapping(target = "decisionTypeId", source = "decisionType.id")
    @Mapping(target = "referralMeetingId", source = "referralMeeting.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "agendaItem", ignore = true)
    @Mapping(target = "decisionType", ignore = true)
    @Mapping(target = "referralMeeting", ignore = true)
    MeetingDecision toEntityForCreate(MeetingDecisionRelationalDto.MeetingDecisionCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "agendaItemId", source = "agendaItem.id")
    @Mapping(target = "decisionTypeId", source = "decisionType.id")
    @Mapping(target = "referralMeetingId", source = "referralMeeting.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "agendaItem", ignore = true)
    @Mapping(target = "decisionType", ignore = true)
    @Mapping(target = "referralMeeting", ignore = true)
    MeetingDecision toEntityForUpdate(MeetingDecisionRelationalDto.MeetingDecisionUpdateDTO updateDto);
}