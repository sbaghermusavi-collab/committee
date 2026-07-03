package com.nicico.committee.repository;

import com.nicico.committee.entities.MeetingQuorum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingQuorumRepository extends JpaRepository<MeetingQuorum, String>, JpaSpecificationExecutor<MeetingQuorum> {
}