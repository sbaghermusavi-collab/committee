package com.nicico.committee.service;

import com.nicico.committee.dto.CommitteeRelationalDto;
import com.nicico.committee.entities.Committee;
import com.nicico.committee.mapper.CommitteeMapper;
import com.nicico.committee.repository.CommitteeRepository;
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
public class CommitteeService {

    private final CommitteeRepository repository;
    private final CommitteeMapper mapper;


    @Transactional(readOnly = true)
    public List<CommitteeRelationalDto.CommitteeInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommitteeRelationalDto.CommitteeInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<CommitteeRelationalDto.CommitteeInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public CommitteeRelationalDto.CommitteeInfoDTO create(CommitteeRelationalDto.CommitteeCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public CommitteeRelationalDto.CommitteeInfoDTO update(String id, CommitteeRelationalDto.CommitteeUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        Committee entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
