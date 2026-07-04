package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.util.List;
import org.hibernate.annotations.Where;
import java.util.Date;

/**
 * Represents the assignment of a member (or alternate) to a committee position.
 */
@Entity
@Table(name = "committee_member_assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommitteeMemberAssignment extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه انتصاب عضو کمیته")
    private String id;

    // ----- CommitteePosition FK -----
    @Column(name = "committee_position_id", nullable = false)
    @Schema(description = "شناسه سمت کمیته")
    private String committeePositionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "committee_position_id", insertable = false, updatable = false)
    @Schema(description = "سمت کمیته")
    private CommitteePosition committeePosition;

    // ----- NicicoPosition (OrganizationalPosition) FK -----
    @Column(name = "nicico_position_id")
    @Schema(description = "شناسه سمت سازمانی در چارت نیکو")
    private String nicicoPositionId;

    // ----- NationalCode -----
    @Column(name = "national_code", nullable = false)
    @Schema(description = "کد ملی عضو")
    private String nationalCode;

    // ----- AlternateMember flag -----
    @Column(name = "alternate_member", nullable = false)
    @Schema(description = "عضو علی‌البدل است")
    private Boolean alternateMember = false;

    // ----- AltNicicoPosition FK -----
    @Column(name = "alt_nicico_position_id")
    @Schema(description = "شناسه سمت سازمانی عضو علی‌البدل")
    private String altNicicoPositionId;

    // ----- AltMemberNationalCode -----
    @Column(name = "alt_member_national_code")
    @Schema(description = "کد ملی عضو علی‌البدل")
    private String altMemberNationalCode;

    // ----- NonicicoTitel (for non-company persons) -----
    @Column(name = "nonicico_titel")
    @Schema(description = "عنوان/سمت برای افراد غیر نیکویی")
    private String nonicicoTitel;

    // ----- StartDate -----
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    @Schema(description = "تاریخ شروع عضویت در کمیته")
    private Date startDate;

    // ----- EndDate -----
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    @Schema(description = "تاریخ پایان عضویت در کمیته")
    private Date endDate;

    // ----- IsExtendDate flag -----
    @Column(name = "is_extend_date", nullable = false)
    @Schema(description = "امکان تمدید تاریخ عضویت")
    private Boolean isExtendDate = true;

    // ----- IsActive flag -----
    @Column(name = "is_active", nullable = false)
    @Schema(description = "وضعیت فعال بودن انتصاب")
    private Boolean isActive = true;

    // ----- DocumentIDs (appointment documents) -----

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "entity_name = 'CommitteeMemberAssignment'")
    private List<EntityDocument> documents;
}