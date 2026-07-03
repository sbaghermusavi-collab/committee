package com.nicico.committee.repository;

import com.nicico.committee.entities.MeetingAgendaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingAgendaItemRepository extends JpaRepository<MeetingAgendaItem, String>, JpaSpecificationExecutor<MeetingAgendaItem> {
}