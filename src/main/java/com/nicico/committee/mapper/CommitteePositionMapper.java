package com.nicico.committee.mapper;

import com.nicico.committee.dto.CommitteePositionRelationalDto;
import com.nicico.committee.entities.CommitteePosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommitteeMapper.class, BaseInfoMapper.class})
public interface CommitteePositionMapper extends DefaultMapper<CommitteePositionRelationalDto.CommitteePositionInfoDTO, CommitteePositionRelationalDto.CommitteePositionUpdateDTO, CommitteePositionRelationalDto.CommitteePositionCreateDTO, CommitteePosition> {

    @Override
    CommitteePositionRelationalDto.CommitteePositionInfoDTO toDto(CommitteePosition entity);

    @Named("toIdDto")
    CommitteePositionRelationalDto.CommitteePositionIdDTO toIdDto(CommitteePosition entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "committeeId", source = "committee.id")
    @Mapping(target = "positionId", source = "position.id")
    @Mapping(target = "presenceTypeId", source = "presenceType.id")
    @Mapping(target = "attendanceConditionId", source = "attendanceCondition.id")
    @Mapping(target = "committee", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "presenceType", ignore = true)
    @Mapping(target = "attendanceCondition", ignore = true)
    CommitteePosition toEntityForCreate(CommitteePositionRelationalDto.CommitteePositionCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "committeeId", source = "committee.id")
    @Mapping(target = "positionId", source = "position.id")
    @Mapping(target = "presenceTypeId", source = "presenceType.id")
    @Mapping(target = "attendanceConditionId", source = "attendanceCondition.id")
    @Mapping(target = "committee", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "presenceType", ignore = true)
    @Mapping(target = "attendanceCondition", ignore = true)
    CommitteePosition toEntityForUpdate(CommitteePositionRelationalDto.CommitteePositionUpdateDTO updateDto);
}