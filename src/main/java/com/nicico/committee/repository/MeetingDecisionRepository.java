package com.nicico.committee.repository;

import com.nicico.committee.entities.MeetingDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingDecisionRepository extends JpaRepository<MeetingDecision, String>, JpaSpecificationExecutor<MeetingDecision> {
}