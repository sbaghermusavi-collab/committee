package com.nicico.committee.controller;

import com.nicico.committee.dto.MeetingAgendaItemRelationalDto;
import com.nicico.committee.service.MeetingAgendaItemService;
import com.nicico.copper.common.dto.search.SearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting-agenda-item")
public class MeetingAgendaItemController {

    private final MeetingAgendaItemService service;


    @GetMapping
    @Operation(summary = "دریافت لیست دستور کارها")
    public ResponseEntity<List<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت دستور کار بر اساس شناسه")
    public ResponseEntity<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد دستور کار")
    public ResponseEntity<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO> create(@RequestBody MeetingAgendaItemRelationalDto.MeetingAgendaItemCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی دستور کار")
    public ResponseEntity<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO> update(@PathVariable String id, @RequestBody MeetingAgendaItemRelationalDto.MeetingAgendaItemUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف دستور کار")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
