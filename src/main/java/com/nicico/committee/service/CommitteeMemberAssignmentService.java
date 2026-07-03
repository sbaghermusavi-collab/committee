package com.nicico.committee.service;

import com.nicico.committee.dto.CommitteeMemberAssignmentRelationalDto;
import com.nicico.committee.entities.CommitteeMemberAssignment;
import com.nicico.committee.mapper.CommitteeMemberAssignmentMapper;
import com.nicico.committee.repository.CommitteeMemberAssignmentRepository;
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
public class CommitteeMemberAssignmentService {

    private final CommitteeMemberAssignmentRepository repository;
    private final CommitteeMemberAssignmentMapper mapper;


    @Transactional(readOnly = true)
    public List<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO create(CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentInfoDTO update(String id, CommitteeMemberAssignmentRelationalDto.CommitteeMemberAssignmentUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        CommitteeMemberAssignment entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }


}
