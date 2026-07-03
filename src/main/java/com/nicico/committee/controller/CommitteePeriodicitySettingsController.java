package com.nicico.committee.controller;

import com.nicico.committee.dto.CommitteePeriodicitySettingsRelationalDto;
import com.nicico.committee.service.CommitteePeriodicitySettingsService;
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
@RequestMapping("/api/committeeperiodicitysettings")

public class CommitteePeriodicitySettingsController {

    private final CommitteePeriodicitySettingsService service;


    @GetMapping
    @Operation(summary = "دریافت لیست")
    public ResponseEntity<List<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت بر اساس شناسه")
    public ResponseEntity<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد رکورد")
    public ResponseEntity<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO> create(@RequestBody CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی")
    public ResponseEntity<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO> update(@PathVariable String id, @RequestBody CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف رکورد")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
