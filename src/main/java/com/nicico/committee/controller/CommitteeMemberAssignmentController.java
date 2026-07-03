package com.nicico.committee.controller;

import com.nicico.committee.dto.CommitteeMemberAssignmentRelationalDto;
import com.nicico.committee.service.CommitteeMemberAssignmentService;
import com.nicico.copper.common.dto.search.SearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/committeememberassignment")

public class CommitteeMemberAssignmentController {

    private final CommitteeMemberAssignmentService service;


    @GetMapping
    @Operation(summary = "دریافت لیست")
    public ResponseEntity<List<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت بر اساس شناسه")
    public ResponseEntity<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد رکورد")
    public ResponseEntity<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO> create(@RequestBody CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی")
    public ResponseEntity<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO> update(@PathVariable String id, @RequestBody CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف رکورد")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
