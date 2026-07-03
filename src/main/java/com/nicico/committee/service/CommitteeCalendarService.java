package com.nicico.committee.service;

import com.nicico.committee.dto.CommitteeCalendarRelationalDto;
import com.nicico.committee.entities.CommitteeCalendar;
import com.nicico.committee.mapper.CommitteeCalendarMapper;
import com.nicico.committee.repository.CommitteeCalendarRepository;
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
public class CommitteeCalendarService {

    private final CommitteeCalendarRepository repository;
    private final CommitteeCalendarMapper mapper;


    @Transactional(readOnly = true)
    public List<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO create(CommitteeCalendarRelationalDto.CommitteeCalendarCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public CommitteeCalendarRelationalDto.CommitteeCalendarInfoDTO update(String id, CommitteeCalendarRelationalDto.CommitteeCalendarUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        CommitteeCalendar entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }

}
