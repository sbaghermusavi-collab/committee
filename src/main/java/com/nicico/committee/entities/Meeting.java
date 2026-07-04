package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import java.util.List;
import org.hibernate.annotations.Where;
import java.time.LocalDate;

@Entity
@Table(name = "meeting")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Meeting extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه جلسه")
    private String id;

    @Column(name = "commission_id", nullable = false)
    @Schema(description = "شناسه کمیسیون")
    private String commissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commission_id", insertable = false, updatable = false)
    @Schema(description = "کمیسیون")
    private Committee commission;

    @Column(name = "code", nullable = false)
    @Schema(description = "کد کلاسه کمیسیون - غیر قابل تغییر")
    private String code;

    @Column(name = "meeting_number")
    @Schema(description = "شماره جلسه - تولید سیستمی بر اساس سال و کد کلاسه")
    private String meetingNumber;

    @Column(name = "meeting_date", nullable = false)
    @Schema(description = "تاریخ برگزاری جلسه")
    private LocalDate meetingDate;

    @Column(name = "meeting_start_time", nullable = false)
    @Schema(description = "زمان شروع جلسه")
    private String meetingStartTime;

    @Column(name = "meeting_end_time")
    @Schema(description = "زمان پایان جلسه - باید بزرگتر از زمان شروع باشد")
    private String meetingEndTime;

    @Column(name = "location_id")
    @Schema(description = "شناسه منطقه برگزاری - از جدول کمیسیون واکشی می‌شود")
    private String locationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    @Schema(description = "منطقه برگزاری")
    private BaseInfo location;

    @Column(name = "address")
    @Schema(description = "مکان برگزاری جلسه")
    private String address;

    @Column(name = "meeting_link")
    @Schema(description = "لینک برگزاری مجازی")
    private String meetingLink;

    @Column(name = "agenda", nullable = false)
    @Schema(description = "شرح دستور جلسه")
    private String agenda;

    @Column(name = "meeting_nature_id", nullable = false)
    @Schema(description = "شناسه نوع جلسه - پیش‌فرض: عادی")
    private String meetingNatureId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_nature_id", insertable = false, updatable = false)
    @Schema(description = "نوع جلسه | Table: نوع جلسه | ID: 4957339b-fe20-4177-a9a3-6475688c6462")
    private BaseInfo meetingNature;

    @Column(name = "status_id", nullable = false)
    @Schema(description = "شناسه وضعیت جلسه - پیش‌فرض: در حال برنامه‌ریزی")
    private String statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    @Schema(description = "وضعیت جلسه | Table: وضعیت جلسه | ID: 536569d2-ca23-49ef-a88f-ba5523a99109")
    private BaseInfo status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "entity_name = 'Meeting'")
    private List<EntityDocument> documents;
}