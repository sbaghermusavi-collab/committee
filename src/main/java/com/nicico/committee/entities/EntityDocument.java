package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "entity_document", indexes = {
    @Index(name = "idx_entity_doc_name_id", columnList = "entity_name, entity_id")
})
public class EntityDocument extends Auditable implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "ID")
    private String id;

    @Column(name = "entity_name", nullable = false)
    @Schema(description = "Name of the entity (e.g., Committee, Meeting)")
    private String entityName;

    @Column(name = "entity_id", nullable = false)
    @Schema(description = "ID of the entity")
    private String entityId;

    @Column(name = "document_id", nullable = false)
    @Schema(description = "ID of the document")
    private String documentId;
}
