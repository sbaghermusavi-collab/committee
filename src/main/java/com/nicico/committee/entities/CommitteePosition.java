package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "committee_position")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CommitteePosition extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه سمت در کمیسیون")
    private String id;

    @Column(name = "committee_id", nullable = false)
    @Schema(description = "شناسه کمیسیون")
    private String committeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "committee_id", insertable = false, updatable = false)
    @Schema(description = "کمیسیون مربوطه")
    private Committee committee;

    @Column(name = "position_id", nullable = false)
    @Schema(description = "شناسه نوع سمت")
    private String positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    @Schema(description = "نوع سمت (اطلاعات پایه) | Table: دسته بندی سمت‌های / نقش در جلسه | ID: ad78e076-7464-4197-ace5-6361c40c45fa")
    private BaseInfo position;

    // ----- IsNicico flag -----
    @Column(name = "is_nicico", nullable = false)
    @Schema(description = "سمت از سازمان داخلی منصوب می‌شود")
    private Boolean isNicico = true;

    @Column(name = "num_member", nullable = false)
    @Schema(description = "تعداد مجاز اعضا")
    private Integer numMember = 1;

    @Column(name = "can_vote", nullable = false)
    @Schema(description = "حق رأی")
    private Boolean canVote = false;

    @Column(name = "can_signature", nullable = false)
    @Schema(description = "حق امضاء")
    private Boolean canSignature = false;

    @Column(name = "can_alternate", nullable = false)
    @Schema(description = "حق جانشینی")
    private Boolean canAlternate = false;

    @Column(name = "can_see_doc", nullable = false)
    @Schema(description = "حق مشاهده اسناد طبقه‌بندی شده | Table: طبقه بندی انواع اسناد | ID: aae08e80-f863-4f23-b905-1f99407089ed")
    private String canSeeDoc;

    @Column(name = "presence_type_id", nullable = false)
    @Schema(description = "شناسه نوع حضور")
    private String presenceTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presence_type_id", insertable = false, updatable = false)
    @Schema(description = "نوع حضور | Table: لیست انواع حضور در جلسه | Parent: f936bf14-b3ef-4bad-829a-68ba481a92d2 | ID: 5d78b96e-56c4-4b52-a5e4-498012c03a28")
    private BaseInfo presenceType;

    @Column(name = "attendance_condition_id", nullable = false)
    @Schema(description = "شناسه شرط حضور")
    private String attendanceConditionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_condition_id", insertable = false, updatable = false)
    @Schema(description = "شرط حضور | Table: لیست انواع شرط حضور در جلسه | Parent: f936bf14-b3ef-4bad-829a-68ba481a92d2 | ID: 5b728d53-8dc9-4bc0-81de-05cd4c24c840")
    private BaseInfo attendanceCondition;

    @Column(name = "is_active", nullable = false)
    @Schema(description = "فعال/غیرفعال")
    private Boolean isActive;
}