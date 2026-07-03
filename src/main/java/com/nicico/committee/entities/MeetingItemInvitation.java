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
@Table(name = "meeting_item_invitation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MeetingItemInvitation extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه دعوت")
    private String id;

    @Column(name = "meeting_id", nullable = false)
    @Schema(description = "شناسه جلسه")
    private String meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", insertable = false, updatable = false)
    @Schema(description = "جلسه")
    private Meeting meeting;

    @Column(name = "agenda_item_id")
    @Schema(description = "شناسه دستور کار - اگر پر شود یعنی دعوت به آیتم خاص")
    private String agendaItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_item_id", insertable = false, updatable = false)
    @Schema(description = "دستور کار")
    private MeetingAgendaItem agendaItem;

    @Column(name = "is_nicico", nullable = false)
    @Schema(description = "پرسنل سازمان است - پیش‌فرض true")
    private Boolean isNicico = true;

    @Column(name = "position_id", nullable = false)
    @Schema(description = "شناسه سمت - پیش‌فرض: مهمان")
    private String positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    @Schema(description = "سمت")
    private BaseInfo position;

    @Column(name = "nicico_position_id")
    @Schema(description = "سمت سازمانی - از جدول سازمان منابع انسانی")
    private String nicicoPositionId;

    @Column(name = "national_code")
    @Schema(description = "کد ملی - نام و نام‌خانوادگی، ایمیل و موبایل واکشی شود")
    private String nationalCode;

    @Column(name = "is_required")
    @Schema(description = "حضور الزامی است - پیش‌فرض true")
    private Boolean isRequired = true;

    @Column(name = "can_vote", nullable = false)
    @Schema(description = "حق رأی - پیش‌فرض false")
    private Boolean canVote = false;

    @Column(name = "can_signature", nullable = false)
    @Schema(description = "حق امضاء - پیش‌فرض false")
    private Boolean canSignature = false;

    @Column(name = "can_see_doc", nullable = false)
    @Schema(description = "حق مشاهده اسناد طبقه‌بندی شده | Table: طبقه بندی انواع اسناد | ID: aae08e80-f863-4f23-b905-1f99407089ed")
    private String canSeeDoc;

    @Column(name = "email")
    @Schema(description = "ایمیل مدعو")
    private String email;

    @Column(name = "phone")
    @Schema(description = "شماره تماس")
    private String phone;

    @Column(name = "company_id")
    @Schema(description = "شناسه سازمان مدعو - تک انتخابی از جدول کمپانی")
    private String companyId;

    @Column(name = "guest_position")
    @Schema(description = "سمت یا نقش مدعو - مثال: مدیر فروش، کارشناس فنی")
    private String guestPosition;

    @Column(name = "invitation_status")
    @Schema(description = "وضعیت پاسخ به دعوت | Table: وضعیت پاسخ به دعوت | ID: b6d7cb20-360a-4311-a9bf-0585d22520e4")
    private String invitationStatus;

    @Column(name = "attendance_status")
    @Schema(description = "وضعیت حضور: حاضر، غایب، تاخیر، مرخصی، آنلاین - پیش‌فرض حاضر")
    private String attendanceStatus;

    @Column(name = "invitation_sent_date")
    @Schema(description = "تاریخ ارسال دعوت")
    private LocalDate invitationSentDate;

    @Column(name = "response_date")
    @Schema(description = "تاریخ پاسخ")
    private LocalDate responseDate;

    @Column(name = "notes")
    @Schema(description = "توضیحات")
    private String notes;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "entity_name = 'MeetingItemInvitation'")
    private List<EntityDocument> documents;
}