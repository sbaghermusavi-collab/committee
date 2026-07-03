package com.nicico.committee.entities;

import com.nicico.committee.entities.enums.BaseInfoType;
import com.nicico.copper.common.domain.Auditable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "base_info", uniqueConstraints = @UniqueConstraint(columnNames = {"type", "latin"},
        name = "uq_base_info_type_latin"))

public class BaseInfo extends Auditable implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BaseInfoType type;

    @Column(nullable = false)
    private String persian;


    @Column(nullable = false)
    private String latin;

    @Column(nullable = false)
    private String code;

    @Setter(AccessLevel.NONE)
    @ManyToOne()
    @JoinColumn(name = "parent_id", updatable = false, insertable = false)
    private BaseInfo parent;

    @Column(name = "parent_id", nullable = false)
    private String parentId;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;


    @Column(name = "description", nullable = false)
    private String description;

//    @OneToOne(mappedBy = "baseInfo", cascade = CascadeType.ALL)
//    @Setter(lombok.AccessLevel.NONE)
//    BaseInfoMenu baseInfoMenu;
}
