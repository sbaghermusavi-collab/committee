package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "meeting_decision")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MeetingDecision extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه تصمیم")
    private String id;

    @Column(name = "meeting_id", nullable = false)
    @Schema(description = "شناسه جلسه")
    private String meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", insertable = false, updatable = false)
    @Schema(description = "جلسه")
    private Meeting meeting;

    @Column(name = "agenda_item_id")
    @Schema(description = "شناسه دستور کار جلسه")
    private String agendaItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_item_id", insertable = false, updatable = false)
    @Schema(description = "دستور کار جلسه")
    private MeetingAgendaItem agendaItem;

    @Column(name = "subject", nullable = false)
    @Schema(description = "خلاصه موضوع تصمیم")
    private String subject;

    @Column(name = "description")
    @Schema(description = "شرح کامل تصمیم")
    private String description;

    @Column(name = "suggester_ids", nullable = false)
    @Schema(description = "پیشنهاد دهندگان - چند انتخابی از اعضاء")
    private String suggesterIds;

    @Column(name = "voting_method", nullable = false)
    @Schema(description = "روش اخذ تصمیم | Table: روش اخذ تصمیم | ID: 503a3f60-0352-48aa-822c-1b0f5a1c95eb")
    private String votingMethod;

    @Column(name = "decision_type_id", nullable = false)
    @Schema(description = "شناسه نوع تصمیم نهایی - تک انتخابی")
    private String decisionTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decision_type_id", insertable = false, updatable = false)
    @Schema(description = "تصمیم نهایی | Table: تصمیمات جلسه | ID: 6ed88c63-2e00-4f7b-9639-96228b901095")
    private BaseInfo decisionType;

    @Column(name = "referral_meeting_id")
    @Schema(description = "ارجاع به جلسه - لیست جلسات مجموعه والد")
    private String referralMeetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referral_meeting_id", insertable = false, updatable = false)
    @Schema(description = "جلسه ارجاع شده")
    private Meeting referralMeeting;

    @Column(name = "decision_date", nullable = false)
    @Schema(description = "تاریخ صدور تصمیم - کمتر از تاریخ جلسه نباشد")
    private LocalDate decisionDate;

    @Column(name = "due_date")
    @Schema(description = "مهلت انجام - بیشتر از تاریخ صدور تصمیم")
    private LocalDate dueDate;

    @Column(name = "is_active", nullable = false)
    @Schema(description = "فعال/غیرفعال - پیش‌فرض فعال")
    private Boolean isActive = true;
}