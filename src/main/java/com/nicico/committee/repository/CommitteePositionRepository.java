package com.nicico.committee.repository;

import com.nicico.committee.entities.CommitteePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommitteePositionRepository extends JpaRepository<CommitteePosition, String>, JpaSpecificationExecutor<CommitteePosition> {
}
