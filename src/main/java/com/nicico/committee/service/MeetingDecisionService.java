package com.nicico.committee.service;

import com.nicico.committee.dto.MeetingDecisionRelationalDto;
import com.nicico.committee.entities.MeetingDecision;
import com.nicico.committee.mapper.MeetingDecisionMapper;
import com.nicico.committee.repository.MeetingDecisionRepository;
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
public class MeetingDecisionService {

    private final MeetingDecisionRepository repository;
    private final MeetingDecisionMapper mapper;


    @Transactional(readOnly = true)
    public List<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetingDecisionRelationalDto.MeetingDecisionInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<MeetingDecisionRelationalDto.MeetingDecisionInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public MeetingDecisionRelationalDto.MeetingDecisionInfoDTO create(MeetingDecisionRelationalDto.MeetingDecisionCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public MeetingDecisionRelationalDto.MeetingDecisionInfoDTO update(String id, MeetingDecisionRelationalDto.MeetingDecisionUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        MeetingDecision entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
