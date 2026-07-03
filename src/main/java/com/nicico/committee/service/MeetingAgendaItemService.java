package com.nicico.committee.service;

import com.nicico.committee.dto.MeetingAgendaItemRelationalDto;
import com.nicico.committee.entities.MeetingAgendaItem;
import com.nicico.committee.mapper.MeetingAgendaItemMapper;
import com.nicico.committee.repository.MeetingAgendaItemRepository;
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
public class MeetingAgendaItemService {

    private final MeetingAgendaItemRepository repository;
    private final MeetingAgendaItemMapper mapper;


    @Transactional(readOnly = true)
    public List<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO create(MeetingAgendaItemRelationalDto.MeetingAgendaItemCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public MeetingAgendaItemRelationalDto.MeetingAgendaItemInfoDTO update(String id, MeetingAgendaItemRelationalDto.MeetingAgendaItemUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        MeetingAgendaItem entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
