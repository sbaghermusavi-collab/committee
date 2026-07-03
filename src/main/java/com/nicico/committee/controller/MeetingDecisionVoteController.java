package com.nicico.committee.controller;

import com.nicico.committee.dto.MeetingDecisionVoteRelationalDto;
import com.nicico.committee.service.MeetingDecisionVoteService;
import com.nicico.copper.common.dto.search.SearchDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meeting-decision-vote")
public class MeetingDecisionVoteController {

    private final MeetingDecisionVoteService service;


    @GetMapping
    @Operation(summary = "دریافت لیست آراء")
    public ResponseEntity<List<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "دریافت رأی بر اساس شناسه")
    public ResponseEntity<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "ثبت رأی")
    public ResponseEntity<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO> create(@RequestBody MeetingDecisionVoteRelationalDto.MeetingDecisionVoteCreateDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی رأی")
    public ResponseEntity<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO> update(@PathVariable String id, @RequestBody MeetingDecisionVoteRelationalDto.MeetingDecisionVoteUpdateDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف رأی")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(service.search(request));
    }
}
