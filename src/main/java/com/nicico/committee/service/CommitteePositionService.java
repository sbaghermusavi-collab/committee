package com.nicico.committee.service;

import com.nicico.committee.dto.CommitteePositionRelationalDto;
import com.nicico.committee.entities.CommitteePosition;
import com.nicico.committee.mapper.CommitteePositionMapper;
import com.nicico.committee.repository.CommitteePositionRepository;
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
public class CommitteePositionService {

    private final CommitteePositionRepository repository;
    private final CommitteePositionMapper mapper;


    @Transactional(readOnly = true)
    public List<CommitteePositionRelationalDto.CommitteePositionInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommitteePositionRelationalDto.CommitteePositionInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<CommitteePositionRelationalDto.CommitteePositionInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public CommitteePositionRelationalDto.CommitteePositionInfoDTO create(CommitteePositionRelationalDto.CommitteePositionCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public CommitteePositionRelationalDto.CommitteePositionInfoDTO update(String id, CommitteePositionRelationalDto.CommitteePositionUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        CommitteePosition entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
