package com.nicico.committee.repository;

import com.nicico.committee.entities.BaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseInfoRepository extends JpaRepository<BaseInfo, String>, JpaSpecificationExecutor<BaseInfo> {
}
