package com.nicico.committee.repository;

import com.nicico.committee.entities.MeetingItemInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MeetingItemInvitationRepository extends JpaRepository<MeetingItemInvitation, String>, JpaSpecificationExecutor<MeetingItemInvitation> {
}