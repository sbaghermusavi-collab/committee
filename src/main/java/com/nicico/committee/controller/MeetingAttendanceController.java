package com.nicico.committee.controller;

import com.nicico.committee.dto.MeetingAttendanceRelationalDto;
import com.nicico.committee.service.MeetingAttendanceService;
import com.nicico.copper.common.dto.search.SearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting-attendance")
public class MeetingAttendanceController {

    private final MeetingAttendanceService service;


    @GetMapping
    @Operation(summary = "دریافت لیست حضور و غیاب")
    public ResponseEntity<List<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت حضور بر اساس شناسه")
    public ResponseEntity<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ثبت حضور")
    public ResponseEntity<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO> create(@RequestBody MeetingAttendanceRelationalDto.MeetingAttendanceCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی حضور")
    public ResponseEntity<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO> update(@PathVariable String id, @RequestBody MeetingAttendanceRelationalDto.MeetingAttendanceUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف حضور")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
