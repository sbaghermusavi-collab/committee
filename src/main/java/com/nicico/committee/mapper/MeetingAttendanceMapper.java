package com.nicico.committee.mapper;

import com.nicico.committee.dto.MeetingAttendanceRelationalDto;
import com.nicico.committee.entities.MeetingAttendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MeetingMapper.class, CommitteeMemberAssignmentMapper.class, BaseInfoMapper.class})
public interface MeetingAttendanceMapper extends DefaultMapper<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO,
        MeetingAttendanceRelationalDto.MeetingAttendanceUpdateDTO, MeetingAttendanceRelationalDto.MeetingAttendanceCreateDTO, MeetingAttendance> {

    @Override
    MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO toDto(MeetingAttendance entity);

    @Named("toIdDto")
    MeetingAttendanceRelationalDto.MeetingAttendanceIdDTO toIdDto(MeetingAttendance entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "assignmentId", source = "assignment.id")
    @Mapping(target = "positionId", source = "position.id")
    @Mapping(target = "presenceTypeId", source = "presenceType.id")
    @Mapping(target = "attendanceConditionId", source = "attendanceCondition.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "assignment", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "presenceType", ignore = true)
    @Mapping(target = "attendanceCondition", ignore = true)
    MeetingAttendance toEntityForCreate(MeetingAttendanceRelationalDto.MeetingAttendanceCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "meetingId", source = "meeting.id")
    @Mapping(target = "assignmentId", source = "assignment.id")
    @Mapping(target = "positionId", source = "position.id")
    @Mapping(target = "presenceTypeId", source = "presenceType.id")
    @Mapping(target = "attendanceConditionId", source = "attendanceCondition.id")
    @Mapping(target = "meeting", ignore = true)
    @Mapping(target = "assignment", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "presenceType", ignore = true)
    @Mapping(target = "attendanceCondition", ignore = true)
    MeetingAttendance toEntityForUpdate(MeetingAttendanceRelationalDto.MeetingAttendanceUpdateDTO updateDto);
}