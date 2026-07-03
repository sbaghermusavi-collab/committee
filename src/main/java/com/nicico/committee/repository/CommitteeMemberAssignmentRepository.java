package com.nicico.committee.repository;

import com.nicico.committee.entities.CommitteeMemberAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommitteeMemberAssignmentRepository extends JpaRepository<CommitteeMemberAssignment, String>, JpaSpecificationExecutor<CommitteeMemberAssignment> {
}
