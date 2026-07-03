package com.nicico.committee.controller;

import com.nicico.committee.dto.CommitteePositionRelationalDto;
import com.nicico.committee.service.CommitteePositionService;
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
@RequestMapping("/api/committeeposition")

public class CommitteePositionController {

    private final CommitteePositionService service;


    @GetMapping
    @Operation(summary = "دریافت لیست")
    public ResponseEntity<List<CommitteePositionRelationalDto.CommitteePositionInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت بر اساس شناسه")
    public ResponseEntity<CommitteePositionRelationalDto.CommitteePositionInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد رکورد")
    public ResponseEntity<CommitteePositionRelationalDto.CommitteePositionInfoDTO> create(@RequestBody CommitteePositionRelationalDto.CommitteePositionCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی")
    public ResponseEntity<CommitteePositionRelationalDto.CommitteePositionInfoDTO> update(@PathVariable String id, @RequestBody CommitteePositionRelationalDto.CommitteePositionUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف رکورد")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<CommitteePositionRelationalDto.CommitteePositionInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
