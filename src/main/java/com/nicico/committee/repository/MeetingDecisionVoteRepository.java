package com.nicico.committee.repository;

import com.nicico.committee.entities.MeetingDecisionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingDecisionVoteRepository extends JpaRepository<MeetingDecisionVote, String>, JpaSpecificationExecutor<MeetingDecisionVote> {
}