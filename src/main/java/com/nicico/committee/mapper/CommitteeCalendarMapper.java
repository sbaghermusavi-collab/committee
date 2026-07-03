package com.nicico.committee.mapper;

import com.nicico.committee.dto.CommitteeCalendarRelationalDto;
import com.nicico.committee.entities.CommitteeCalendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                CommitteeMapper.class,
                BaseInfoMapper.class,
                CommitteePositionMapper.class
        }
)
public interface CommitteeCalendarMapper extends DefaultMapper<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO,
        CommitteeCalendarRelationalDto.CommitteeCalendarUpdateDTO, CommitteeCalendarRelationalDto.CommitteeCalendarCreateDTO, CommitteeCalendar> {

    @Override
    @Mapping(target = "periodicitySettings", ignore = true)
    CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO toDto(CommitteeCalendar entity);

    @Named("toIdDto")
    CommitteeCalendarRelationalDto.CommitteeCalendarIdDTO toIdDto(CommitteeCalendar entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "committeeId", source = "committee.id")
    @Mapping(target = "periodicityId", source = "periodicity.id")
    @Mapping(target = "meetingModelId", source = "meetingModel.id")
    @Mapping(target = "responsibleCommitteePositionId", source = "responsibleCommitteePosition.id")
    @Mapping(target = "committee", ignore = true)
    @Mapping(target = "periodicity", ignore = true)
    @Mapping(target = "meetingModel", ignore = true)
    @Mapping(target = "responsibleCommitteePosition", ignore = true)
    @Mapping(target = "periodicitySettings", ignore = true)
    CommitteeCalendar toEntityForCreate(CommitteeCalendarRelationalDto.CommitteeCalendarCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "committeeId", source = "committee.id")
    @Mapping(target = "periodicityId", source = "periodicity.id")
    @Mapping(target = "meetingModelId", source = "meetingModel.id")
    @Mapping(target = "responsibleCommitteePositionId", source = "responsibleCommitteePosition.id")
    @Mapping(target = "committee", ignore = true)
    @Mapping(target = "periodicity", ignore = true)
    @Mapping(target = "meetingModel", ignore = true)
    @Mapping(target = "responsibleCommitteePosition", ignore = true)
    @Mapping(target = "periodicitySettings", ignore = true)
    CommitteeCalendar toEntityForUpdate(CommitteeCalendarRelationalDto.CommitteeCalendarUpdateDTO updateDto);
}
