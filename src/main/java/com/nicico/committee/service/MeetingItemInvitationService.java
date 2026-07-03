package com.nicico.committee.service;

import com.nicico.committee.dto.MeetingItemInvitationRelationalDto;
import com.nicico.committee.entities.MeetingItemInvitation;
import com.nicico.committee.mapper.MeetingItemInvitationMapper;
import com.nicico.committee.repository.MeetingItemInvitationRepository;
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
public class MeetingItemInvitationService {

    private final MeetingItemInvitationRepository repository;
    private final MeetingItemInvitationMapper mapper;


    @Transactional(readOnly = true)
    public List<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO findById(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found: " + id)));
    }

    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(repository, query, mapper::toDto);
    }

    public MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO create(MeetingItemInvitationRelationalDto.MeetingItemInvitationCreateDTO request) {
        return mapper.toDto(repository.save(mapper.toEntityForCreate(request)));
    }

    public MeetingItemInvitationRelationalDto.MeetingItemInvitationInfoDTO update(String id, MeetingItemInvitationRelationalDto.MeetingItemInvitationUpdateDTO request) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        MeetingItemInvitation entity = mapper.toEntityForUpdate(request);
        entity.setId(id);
        return mapper.toDto(repository.save(entity));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Entity not found: " + id);
        repository.deleteById(id);
    }
}
