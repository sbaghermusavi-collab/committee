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

@Entity
@Table(name = "committee")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Committee extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه کمیسیون")
    private String id;

    // ----- Category FK (BaseInfo) -----
    @Column(name = "category_id", nullable = false)
    @Schema(description = "شناسه ماهیت سازمانی")
    private String categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @Schema(description = "ماهیت سازمانی (اطلاعات پایه) | Table: ماهیت سازمانی جلسه | ID: 1e6aa780-517c-42fe-b531-3044b14a0d48")
    private BaseInfo category;

    // ----- BusinessDomain FK (BaseInfo) -----
    @Column(name = "business_domain_id", nullable = false)
    @Schema(description = "شناسه حوزه کسب و کار")
    private String businessDomainId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_domain_id", insertable = false, updatable = false)
    @Schema(description = "حوزه کسب و کار (اطلاعات پایه) | Table: حوزه‌های تخصصی کسب و کار | ID: 93bb9163-290e-448b-a936-6ea1122243d1")
    private BaseInfo businessDomain;

    // ----- SubjectCategory FK (BaseInfo) -----
    @Column(name = "subject_category_id", nullable = false)
    @Schema(description = "شناسه حوزه موضوعی")
    private String subjectCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_category_id", insertable = false, updatable = false)
    @Schema(description = "حوزه موضوعی (اطلاعات پایه) | Table: حوزه موضوعی جلسه | ID: af321485-7578-42d0-b21e-2d268c0a0921")
    private BaseInfo subjectCategory;

    // ----- Parent Committee FK -----
    @Column(name = "parent_committee_id")
    @Schema(description = "شناسه کمیسیون بالادست")
    private String parentCommitteeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_committee_id", insertable = false, updatable = false)
    @Schema(description = "کمیسیون بالادست")
    private Committee parentCommittee;

    // ----- SystemOwner FK -----
    @Column(name = "system_owner_id", nullable = false)
    @Schema(description = "شناسه مالک سامانه")
    private String systemOwnerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_owner_id", insertable = false, updatable = false)
    @Schema(description = "مالک سامانه (اطلاعات پایه) | Table: سیستم مالک | Parent: d9e4548a-0ed3-4407-b634-d581347bd3a0 | ID: a7be017d-c7b1-4570-9f41-f4d166d7c254")
    private BaseInfo systemOwner;

    // ----- IsNicico flag -----
    @Column(name = "is_nicico", nullable = false)
    @Schema(description = "مسئول جلسه از سازمان داخلی است")
    private Boolean isNicico = true;

    // ----- CommitteeOwner FK -----
    @Column(name = "committee_owner_id")
    @Schema(description = "شناسه سازمان متولی جلسه")
    private String committeeOwnerId;

    // ----- CommitteeOwnerTitel -----
    @Column(name = "committee_owner_titel")
    @Schema(description = "عنوان متولی کمیسیون")
    private String committeeOwnerTitel;

    // ----- CommitteeLocation FK -----
    @Column(name = "committee_location_id", nullable = false)
    @Schema(description = "شناسه منطقه برگزاری کمیسیون")
    private String committeeLocationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "committee_location_id", insertable = false, updatable = false)
    @Schema(description = "منطقه برگزاری (اطلاعات پایه)")
    private BaseInfo committeeLocation;

    // ----- CommitteeAddress -----
    @Column(name = "committee_address", nullable = false)
    @Schema(description = "آدرس محل برگزاری")
    private String committeeAddress;

    // ----- CommitteeTime -----
    @Column(name = "committee_time")
    @Schema(description = "ساعت برگزاری کمیسیون")
    private String committeeTime;

    // ----- Code -----
    @Column(name = "code", nullable = false, unique = true)
    @Schema(description = "کد اختصاری کمیسیون")
    private String code;

    // ----- Name -----
    @Column(name = "name", nullable = false)
    @Schema(description = "نام فارسی کمیسیون")
    private String name;

    // ----- Description -----
    @Column(name = "description")
    @Schema(description = "توضیحات کمیسیون")
    private String description;

    // ----- CommitteeLevel FK -----
    @Column(name = "committee_level_id", nullable = false)
    @Schema(description = "شناسه سطح کمیسیون")
    private String committeeLevelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "committee_level_id", insertable = false, updatable = false)
    @Schema(description = "سطح جلسه (اطلاعات پایه) | Table: سطح جلسه یا کمیسیون | ID: d958f5fb-abef-4d06-ba5f-2b59b13bb668")
    private BaseInfo committeeLevel;

    // ----- Document IDs -----

    // ----- MinMembersCommittee -----
    @Column(name = "min_members_committee", nullable = false)
    @Schema(description = "حداقل اعضای لازم برای رسمیت جلسه")
    private Integer minMembersCommittee;

    // ----- IsExtendDate -----
    @Column(name = "is_extend_date", nullable = false)
    @Schema(description = "قابلیت تمدید")
    private Boolean isExtendDate = true;

    // ----- IsActive -----
    @Column(name = "is_active", nullable = false)
    @Schema(description = "فعال/غیرفعال")
    private Boolean isActive = true;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "entity_name = 'Committee'")
    private List<EntityDocument> documents;
}