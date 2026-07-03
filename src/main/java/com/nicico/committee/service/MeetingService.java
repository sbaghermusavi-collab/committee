package com.nicico.committee.service;

import com.nicico.committee.dto.MeetingRelationalDto;
import com.nicico.committee.entities.Meeting;
import com.nicico.committee.mapper.MeetingMapper;
import com.nicico.committee.repository.MeetingRepository;
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
public class MeetingService {

    private final MeetingRepository repository;
    private final MeetingMapper mapper;

    @Transactional(readOnly = true)
    public List<MeetingRelationalDto.MeetingInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetingRelationalDto.MeetingInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<MeetingRelationalDto.MeetingInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public MeetingRelationalDto.MeetingInfoDTO create(MeetingRelationalDto.MeetingCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public MeetingRelationalDto.MeetingInfoDTO update(String id, MeetingRelationalDto.MeetingUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        Meeting entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
