package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "meeting_quorum")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MeetingQuorum extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه حد نصاب")
    private String id;

    @Column(name = "meeting_id", nullable = false)
    @Schema(description = "شناسه جلسه")
    private String meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", insertable = false, updatable = false)
    @Schema(description = "جلسه")
    private Meeting meeting;

    @Column(name = "required_members", nullable = false)
    @Schema(description = "تعداد حاضرین الزامی")
    private Integer requiredMembers;

    @Column(name = "actual_present", nullable = false)
    @Schema(description = "تعداد کل حاضرین")
    private Integer actualPresent;

    @Column(name = "is_valid", nullable = false)
    @Schema(description = "رسمیت جلسه")
    private Boolean isValid;

    @Column(name = "meeting_invalid_reason_ids")
    @Schema(description = "دلایل عدم برگزاری جلسه | Table: دلایل عدم برگزاری جلسه | ID: 43c15f1a-f5d1-42eb-a4b3-dd1429784e7a")
    private String meetingInvalidReasonIds;

    @Column(name = "quorum_note")
    @Schema(description = "توضیح در صورت عدم رسمیت")
    private String quorumNote;
}