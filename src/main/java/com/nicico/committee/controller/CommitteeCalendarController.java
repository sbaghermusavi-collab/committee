package com.nicico.committee.controller;

import com.nicico.committee.dto.CommitteeCalendarRelationalDto;
import com.nicico.committee.service.CommitteeCalendarService;
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
@RequestMapping("/api/committeecalendar")

public class CommitteeCalendarController {

    private final CommitteeCalendarService service;


    @GetMapping
    @Operation(summary = "دریافت لیست")
    public ResponseEntity<List<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت بر اساس شناسه")
    public ResponseEntity<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد رکورد")
    public ResponseEntity<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO> create(@RequestBody CommitteeCalendarRelationalDto.CommitteeCalendarCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی")
    public ResponseEntity<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO> update(@PathVariable String id, @RequestBody CommitteeCalendarRelationalDto.CommitteeCalendarUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف رکورد")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
