package com.nicico.committee.repository;

import com.nicico.committee.entities.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommitteeRepository extends JpaRepository<Committee, String>, JpaSpecificationExecutor<Committee> {
}
