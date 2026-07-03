package com.nicico.committee.service;

import com.nicico.committee.dto.MeetingQuorumRelationalDto;
import com.nicico.committee.entities.MeetingQuorum;
import com.nicico.committee.mapper.MeetingQuorumMapper;
import com.nicico.committee.repository.MeetingQuorumRepository;
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
public class MeetingQuorumService {

    private final MeetingQuorumRepository repository;
    private final MeetingQuorumMapper mapper;


    @Transactional(readOnly = true)
    public List<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetingQuorumRelationalDto.MeetingQuorumInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<MeetingQuorumRelationalDto.MeetingQuorumInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public MeetingQuorumRelationalDto.MeetingQuorumInfoDTO create(MeetingQuorumRelationalDto.MeetingQuorumCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public MeetingQuorumRelationalDto.MeetingQuorumInfoDTO update(String id, MeetingQuorumRelationalDto.MeetingQuorumUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        MeetingQuorum entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
