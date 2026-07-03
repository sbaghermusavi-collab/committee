package com.nicico.committee.repository;

import com.nicico.committee.entities.CommitteePeriodicitySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommitteePeriodicitySettingsRepository extends JpaRepository<CommitteePeriodicitySettings, String>, JpaSpecificationExecutor<CommitteePeriodicitySettings> {
}
