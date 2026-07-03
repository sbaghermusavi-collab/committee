package com.nicico.committee.mapper;

import com.nicico.committee.dto.CommitteePeriodicitySettingsRelationalDto;
import com.nicico.committee.entities.CommitteePeriodicitySettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CommitteeCalendarMapper.class)
public interface CommitteePeriodicitySettingsMapper extends DefaultMapper<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO,
        CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsUpdateDTO, CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsCreateDTO, CommitteePeriodicitySettings> {

    @Override
    CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO toDto(CommitteePeriodicitySettings entity);

    @Named("toIdDto")
    CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsIdDTO toIdDto(CommitteePeriodicitySettings entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarId", source = "calendar.id")
    @Mapping(target = "calendar", ignore = true)
    CommitteePeriodicitySettings toEntityForCreate(CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "calendarId", source = "calendar.id")
    @Mapping(target = "calendar", ignore = true)
    CommitteePeriodicitySettings toEntityForUpdate(CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsUpdateDTO updateDto);
}
