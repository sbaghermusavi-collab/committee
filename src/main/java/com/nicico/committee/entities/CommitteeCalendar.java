package com.nicico.committee.entities;

import com.nicico.committee.entities.BaseInfo;
import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "committee_calendar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommitteeCalendar extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه تقویم کمیته")
    private String id;

    @Column(name = "committee_id", nullable = false)
    @Schema(description = "شناسه کمیته")
    private String committeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "committee_id", insertable = false, updatable = false)
    @Schema(description = "کمیته مربوطه")
    private Committee committee;

    @Column(name = "periodicity_id", nullable = false)
    @Schema(description = "شناسه تناوب برگزاری جلسات")
    private String periodicityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodicity_id", insertable = false, updatable = false)
    @Schema(description = "دوره تناوب اصلی | Table: دسته بندی دوره‌های تناوب برگزاری جلسات | ID: fa904e3f-8fa2-4b44-ba0e-5e6e89c4cfa6")
    private BaseInfo periodicity;

    @Column(name = "meeting_model_id", nullable = false)
    @Schema(description = "شناسه مدل برگزاری جلسه")
    private String meetingModelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_model_id", insertable = false, updatable = false)
    @Schema(description = "نحوه برگزاری | Table: نحوی برگزاری جلسه | ID: e24d5684-b607-44bc-a3f1-a273e5f92ddf")
    private BaseInfo meetingModel;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date_committee", nullable = false)
    @Schema(description = "تاریخ شروع اعتبار تقویم کمیته")
    private Date startDateCommittee;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date_committee")
    @Schema(description = "تاریخ پایان اعتبار تقویم کمیته")
    private Date endDateCommittee;

    @Temporal(TemporalType.DATE)
    @Column(name = "first_date_meeting")
    @Schema(description = "اولین تاریخ برگزاری جلسه")
    private Date firstDateMeeting;

    @Temporal(TemporalType.DATE)
    @Column(name = "next_plan_date_meeting")
    @Schema(description = "تاریخ برنامه‌ریزی‌شده جلسه بعدی")
    private Date nextPlanDateMeeting;

    @Column(name = "floating_days")
    @Schema(description = "تعداد روزهای شناور (قبل/بعد از تاریخ اصلی) برای تنظیم جلسات")
    private Integer floatingDays;

    @Column(name = "responsible_committee_position_id", nullable = false)
    @Schema(description = "شناسه سمت مسئول برنامه‌ریزی کمیته")
    private String responsibleCommitteePositionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_committee_position_id", insertable = false, updatable = false)
    @Schema(description = "سمت مسئول برنامه‌ریزی کمیته")
    private CommitteePosition responsibleCommitteePosition;

    @Column(name = "is_active", nullable = false)
    @Schema(description = "وضعیت فعال بودن تقویم")
    private Boolean isActive = true;

    @OneToOne(mappedBy = "calendar", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Schema(description = "تنظیمات تفصیلی تناوب جلسات")
    private CommitteePeriodicitySettings periodicitySettings;
}