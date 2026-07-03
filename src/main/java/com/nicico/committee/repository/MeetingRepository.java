package com.nicico.committee.repository;

import com.nicico.committee.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingRepository extends JpaRepository<Meeting, String>, JpaSpecificationExecutor<Meeting> {
}