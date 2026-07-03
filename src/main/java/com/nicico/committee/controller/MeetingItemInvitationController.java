package com.nicico.committee.controller;

import com.nicico.committee.dto.MeetingItemInvitationRelationalDto;
import com.nicico.committee.service.MeetingItemInvitationService;
import com.nicico.copper.common.dto.search.SearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting-invitation")
public class MeetingItemInvitationController {

    private final MeetingItemInvitationService service;


    @GetMapping
    @Operation(summary = "دریافت لیست دعوت‌ها")
    public ResponseEntity<List<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت دعوت بر اساس شناسه")
    public ResponseEntity<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ایجاد دعوت")
    public ResponseEntity<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO> create(@RequestBody MeetingItemInvitationRelationalDto.MeetingItemInvitationCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی دعوت")
    public ResponseEntity<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO> update(@PathVariable String id, @RequestBody MeetingItemInvitationRelationalDto.MeetingItemInvitationUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف دعوت")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
