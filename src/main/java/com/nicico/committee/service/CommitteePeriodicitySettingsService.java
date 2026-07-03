package com.nicico.committee.service;

import com.nicico.committee.dto.CommitteePeriodicitySettingsRelationalDto;
import com.nicico.committee.entities.CommitteePeriodicitySettings;
import com.nicico.committee.mapper.CommitteePeriodicitySettingsMapper;
import com.nicico.committee.repository.CommitteePeriodicitySettingsRepository;
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
public class CommitteePeriodicitySettingsService {

    private final CommitteePeriodicitySettingsRepository repository;
    private final CommitteePeriodicitySettingsMapper mapper;

    @Transactional(readOnly = true)
    public List<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO create(CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsInfoDTO update(String id, CommitteePeriodicitySettingsRelationalDto.CommitteePeriodicitySettingsUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        CommitteePeriodicitySettings entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }

}
