package com.nicico.committee.service;

import com.nicico.committee.dto.MeetingAttendanceRelationalDto;
import com.nicico.committee.entities.MeetingAttendance;
import com.nicico.committee.mapper.MeetingAttendanceMapper;
import com.nicico.committee.repository.MeetingAttendanceRepository;
import com.nicico.copper.common.domain.criteria.SearchUtil;
import com.nicico.copper.common.dto.search.SearchDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingAttendanceService {

    private final MeetingAttendanceRepository repository;
    private final MeetingAttendanceMapper mapper;


    @Transactional(readOnly = true)
    public List<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO create(MeetingAttendanceRelationalDto.MeetingAttendanceCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public MeetingAttendanceRelationalDto.MeetingAttendanceInfoDTO update(String id, MeetingAttendanceRelationalDto.MeetingAttendanceUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        MeetingAttendance entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
