package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "meeting_attendance")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MeetingAttendance extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه حضور در جلسه")
    private String id;

    @Column(name = "meeting_id", nullable = false)
    @Schema(description = "شناسه جلسه")
    private String meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", insertable = false, updatable = false)
    @Schema(description = "جلسه")
    private Meeting meeting;

    @Column(name = "assignment_id", nullable = false)
    @Schema(description = "شناسه انتصاب عضو")
    private String assignmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", insertable = false, updatable = false)
    @Schema(description = "انتصاب عضو")
    private CommitteeMemberAssignment assignment;

    @Column(name = "attendance_status", nullable = false)
    @Schema(description = "وضعیت حضور: Present, Absent, Excused, Delegated, Guest")
    private String attendanceStatus;

    @Column(name = "delegate_member_id")
    @Schema(description = "شناسه عضو جانشین - از جدول CommissionMemberAssignment")
    private String delegateMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delegate_member_id", insertable = false, updatable = false)
    @Schema(description = "عضو جانشین")
    private CommitteeMemberAssignment delegateMember;

    @Column(name = "delegate_status")
    @Schema(description = "وضعیت حضور جانشین: Present, Absent, Excused")
    private String delegateStatus;

    @Column(name = "signed_minutes", nullable = false)
    @Schema(description = "آیا عضو صورتجلسه را امضا کرده - پیش‌فرض false")
    private Boolean signedMinutes = false;

    @Column(name = "position_id", nullable = false)
    @Schema(description = "شناسه سمت - غیرقابل ویرایش")
    private String positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    @Schema(description = "سمت | Table: دسته بندی سمت‌های / نقش در جلسه | ID: ad78e076-7464-4197-ace5-6361c40c45fa")
    private BaseInfo position;

    @Column(name = "is_nicico", nullable = false)
    @Schema(description = "سمت سازمانی - از جدول انتصاب عضو واکشی می‌شود")
    private Boolean isNicico = true;

    @Column(name = "can_vote", nullable = false)
    @Schema(description = "حق رأی - غیرقابل ویرایش")
    private Boolean canVote = false;

    @Column(name = "can_signature", nullable = false)
    @Schema(description = "حق امضاء - غیرقابل ویرایش")
    private Boolean canSignature = false;

    @Column(name = "can_see_doc", nullable = false)
    @Schema(description = "حق مشاهده اسناد طبقه‌بندی شده | Table: طبقه بندی انواع اسناد | ID: aae08e80-f863-4f23-b905-1f99407089ed")
    private String canSeeDoc;

    @Column(name = "presence_type_id", nullable = false)
    @Schema(description = "شناسه نوع حضور - غیرقابل ویرایش")
    private String presenceTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presence_type_id", insertable = false, updatable = false)
    @Schema(description = "نوع حضور | Table: لیست انواع حضور در جلسه | Parent: f936bf14-b3ef-4bad-829a-68ba481a92d2 | ID: 5d78b96e-56c4-4b52-a5e4-498012c03a28")
    private BaseInfo presenceType;

    @Column(name = "attendance_condition_id", nullable = false)
    @Schema(description = "شناسه شرط حضور - غیرقابل ویرایش")
    private String attendanceConditionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_condition_id", insertable = false, updatable = false)
    @Schema(description = "شرط حضور | Table: لیست انواع شرط حضور در جلسه | Parent: f936bf14-b3ef-4bad-829a-68ba481a92d2 | ID: 5b728d53-8dc9-4bc0-81de-05cd4c24c840")
    private BaseInfo attendanceCondition;

    @Column(name = "nicico_position_id")
    @Schema(description = "شناسه سمت سازمانی - از جدول سازمان منابع انسانی")
    private String nicicoPositionId;

    @Column(name = "national_code", nullable = false)
    @Schema(description = "کد ملی عضو")
    private String nationalCode;

    @Column(name = "alternate_member", nullable = false)
    @Schema(description = "عضو علی‌البدل - پیش‌فرض false")
    private Boolean alternateMember = false;

    @Column(name = "alt_nicico_position_id")
    @Schema(description = "سمت سازمانی عضو علی‌البدل")
    private String altNicicoPositionId;

    @Column(name = "alt_member_national_code")
    @Schema(description = "کد ملی عضو علی‌البدل")
    private String altMemberNationalCode;

    @Column(name = "nonicico_titel")
    @Schema(description = "سمت سازمانی فرد غیر شرکتی")
    private String nonicicoTitel;

    @Column(name = "signed_attendance_document_id")
    @Schema(description = "سند امضاء لیست حضور - تک انتخابی")
    private String signedAttendanceDocumentId;

    @Column(name = "document_ids")
    @Schema(description = "مستند رسمی انتصاب | Table: روش اخذ تصمیم | ID: 503a3f60-0352-48aa-822c-1b0f5a1c95eb")
    private String documentIds;

    @Column(name = "document_link_ids")
    @Schema(description = "اسناد مرتبط - چند انتخابی")
    private String documentLinkIds;
}