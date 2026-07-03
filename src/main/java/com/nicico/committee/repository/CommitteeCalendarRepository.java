package com.nicico.committee.repository;

import com.nicico.committee.entities.CommitteeCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommitteeCalendarRepository extends JpaRepository<CommitteeCalendar, String>, JpaSpecificationExecutor<CommitteeCalendar> {
}
