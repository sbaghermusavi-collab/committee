package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "meeting_decision_vote")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MeetingDecisionVote extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه رأی")
    private String id;

    @Column(name = "decision_id", nullable = false)
    @Schema(description = "شناسه تصمیم")
    private String decisionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decision_id", insertable = false, updatable = false)
    @Schema(description = "تصمیم")
    private MeetingDecision decision;

    @Column(name = "member_id", nullable = false)
    @Schema(description = "شناسه عضو کمیسیون - از لیست اعضاء حاضر")
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @Schema(description = "عضو کمیسیون")
    private CommitteeMemberAssignment member;

    @Column(name = "national_code")
    @Schema(description = "کد ملی عضو")
    private String nationalCode;

    // 🔧 اصلاح: nullable = false اضافه شد
    @Column(name = "vote_type", nullable = false)
    @Schema(description = "نوع رأی: موافق، مخالف، موافق مشروط، ممتنع، عدم مشارکت")
    private String voteType;

    @Column(name = "conditional_description")
    @Schema(description = "شرح شرط رأی مشروط")
    private String conditionalDescription;

    @Column(name = "hidden_vote", nullable = false)
    @Schema(description = "رأی دهنده قابل مشاهده - پیش‌فرض بله")
    private Boolean hiddenVote = true;
}