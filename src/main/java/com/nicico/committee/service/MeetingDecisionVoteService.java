package com.nicico.committee.service;

import com.nicico.committee.dto.MeetingDecisionVoteRelationalDto;
import com.nicico.committee.entities.MeetingDecisionVote;
import com.nicico.committee.mapper.MeetingDecisionVoteMapper;
import com.nicico.committee.repository.MeetingDecisionVoteRepository;
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
public class MeetingDecisionVoteService {

    private final MeetingDecisionVoteRepository repository;
    private final MeetingDecisionVoteMapper mapper;


    @Transactional(readOnly = true)
    public List<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO create(MeetingDecisionVoteRelationalDto.MeetingDecisionVoteCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public MeetingDecisionVoteRelationalDto.MeetingDecisionVoteInfoDTO update(String id, MeetingDecisionVoteRelationalDto.MeetingDecisionVoteUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        MeetingDecisionVote entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
