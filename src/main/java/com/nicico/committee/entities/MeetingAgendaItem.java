package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "meeting_agenda_item")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MeetingAgendaItem extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه آیتم دستور جلسه")
    private String id;

    @Column(name = "meeting_id", nullable = false)
    @Schema(description = "شناسه جلسه")
    private String meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", insertable = false, updatable = false)
    @Schema(description = "جلسه")
    private Meeting meeting;

    @Column(name = "item_order", nullable = false)
    @Schema(description = "ردیف آیتم")
    private Integer itemOrder;

    @Column(name = "presentation_count", nullable = false)
    @Schema(description = "تعداد دفعات طرح در جلسه")
    private Integer presentationCount = 1;

    @Column(name = "title", nullable = false)
    @Schema(description = "عنوان آیتم")
    private String title;

    @Column(name = "description")
    @Schema(description = "توضیحات اقدام لازم")
    private String description;

    @Column(name = "agenda_decision_type_id", nullable = false)
    @Schema(description = "شناسه نوع تصمیم اخذ شده")
    private String agendaDecisionTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_decision_type_id", insertable = false, updatable = false)
    @Schema(description = "تصمیم اخذ شده | Table: تصمیمات جلسه | ID: 6ed88c63-2e00-4f7b-9639-96228b901095")
    private BaseInfo agendaDecisionType;

    @Column(name = "decision")
    @Schema(description = "توضیح تصمیم اتخاذ شده")
    private String decision;

    @Column(name = "agenda_item_status_id")
    @Schema(description = "شناسه وضعیت")
    private String agendaItemStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_item_status_id", insertable = false, updatable = false)
    @Schema(description = "وضعیت دستور کار | Table: وضعیت دستور کارهای جلسه | ID: 8db00681-0d7f-4e2d-9d73-23dcc0f9a94e")
    private BaseInfo agendaItemStatus;
}