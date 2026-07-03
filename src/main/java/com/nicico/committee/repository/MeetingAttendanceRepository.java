package com.nicico.committee.repository;

import com.nicico.committee.entities.MeetingAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingAttendanceRepository extends JpaRepository<MeetingAttendance, String>, JpaSpecificationExecutor<MeetingAttendance> {
}