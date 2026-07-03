package com.nicico.committee.controller;

import com.nicico.committee.dto.BaseInfoRelationalDto;
import com.nicico.committee.service.BaseInfoService;
import com.nicico.copper.common.dto.search.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/base-info")
public class BaseInfoController {

    private final BaseInfoService baseInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseInfoRelationalDto.BaseInfoInfoDTO> get(@PathVariable String id) {
        return new ResponseEntity<>(baseInfoService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BaseInfoRelationalDto.BaseInfoInfoDTO>> list() {
        return new ResponseEntity<>(baseInfoService.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseInfoRelationalDto.BaseInfoInfoDTO> create(@RequestBody BaseInfoRelationalDto.BaseInfoCreateDTO request) {
        return new ResponseEntity<>(baseInfoService.create(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BaseInfoRelationalDto.BaseInfoInfoDTO> update(@RequestBody BaseInfoRelationalDto.BaseInfoUpdateDTO request) {
        return new ResponseEntity<>(baseInfoService.update(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        baseInfoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<SearchDTO.SearchRs<BaseInfoRelationalDto.BaseInfoInfoDTO>> search(@RequestBody SearchDTO.SearchRq request) {
        return ResponseEntity.ok(baseInfoService.search(request));
    }
}
