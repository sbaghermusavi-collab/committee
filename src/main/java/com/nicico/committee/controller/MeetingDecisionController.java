package com.nicico.committee.controller;

import com.nicico.committee.dto.MeetingDecisionRelationalDto;
import com.nicico.committee.service.MeetingDecisionService;
import com.nicico.copper.common.dto.search.SearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting-decision")
public class MeetingDecisionController {

    private final MeetingDecisionService service;


    @GetMapping
    @Operation(summary = "دریافت لیست تصمیمات")
    public ResponseEntity<List<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت تصمیم بر اساس شناسه")
    public ResponseEntity<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد تصمیم")
    public ResponseEntity<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO> create(@RequestBody MeetingDecisionRelationalDto.MeetingDecisionCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی تصمیم")
    public ResponseEntity<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO> update(@PathVariable String id, @RequestBody MeetingDecisionRelationalDto.MeetingDecisionUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف تصمیم")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
