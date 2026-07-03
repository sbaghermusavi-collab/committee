package com.nicico.committee.mapper;

import com.nicico.committee.dto.MeetingItemInvitationRelationalDto;
import com.nicico.committee.entities.MeetingItemInvitation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MeetingMapper.class, MeetingAgendaItemMapper.class, BaseInfoMapper.class})
public interface MeetingItemInvitationMapper extends DefaultMapper<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO,
        MeetingItemInvitationRelationalDto.MeetingItemInvitationUpdateDTO, MeetingItemInvitationRelationalDto.MeetingItemInvitationCreateDTO, MeetingItemInvitation> {

    @Override
    MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO toDto(MeetingItemInvitation entity);

    @Named("toIdDto")
    MeetingItemInvitationRelationalDto.MeetingItemInvitationIdDTO toIdDto(MeetingItemInvitation entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "agendaItemId", source = "agendaItem.id")
    @Mapping(target = "positionId", source = "position.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "agendaItem", ignore = true)
    @Mapping(target = "position", ignore = true)
    MeetingItemInvitation toEntityForCreate(MeetingItemInvitationRelationalDto.MeetingItemInvitationCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "agendaItemId", source = "agendaItem.id")
    @Mapping(target = "positionId", source = "position.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "agendaItem", ignore = true)
    @Mapping(target = "position", ignore = true)
    MeetingItemInvitation toEntityForUpdate(MeetingItemInvitationRelationalDto.MeetingItemInvitationUpdateDTO updateDto);
}