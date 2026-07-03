package com.nicico.committee.controller;

import com.nicico.committee.dto.MeetingQuorumRelationalDto;
import com.nicico.committee.service.MeetingQuorumService;
import com.nicico.copper.common.dto.search.SearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting-quorum")
public class MeetingQuorumController {

    private final MeetingQuorumService service;


    @GetMapping
    @Operation(summary = "دریافت لیست حد نصاب‌ها")
    public ResponseEntity<List<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت حد نصاب بر اساس شناسه")
    public ResponseEntity<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد حد نصاب")
    public ResponseEntity<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO> create(@RequestBody MeetingQuorumRelationalDto.MeetingQuorumCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی حد نصاب")
    public ResponseEntity<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO> update(@PathVariable String id, @RequestBody MeetingQuorumRelationalDto.MeetingQuorumUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف حد نصاب")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
