//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.nicico.copper.common.domain;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass

@EntityListeners({AuditingEntityListener.class})
public abstract class Auditable {
    @CreatedDate
    @Column(
        name = "d_created_date",
        nullable = false,
        updatable = false
    )
    private Date createdDate;
    @CreatedBy
    @Column(
        name = "c_created_by",
        nullable = false,
        updatable = false
    )
    private String createdBy;
    @LastModifiedDate
    @Column(
        name = "d_last_modified_date"
    )
    private Date lastModifiedDate;
    @LastModifiedBy
    @Column(
        name = "c_last_modified_by"
    )
    private String lastModifiedBy;
    @Version
    @Column(
        name = "n_version",
        nullable = false
    )
    private Integer version;

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Auditable setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Auditable setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Auditable setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public Auditable setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public Auditable setVersion(final Integer version) {
        this.version = version;
        return this;
    }
}
