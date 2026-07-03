package com.nicico.committee.service;

import com.nicico.committee.dto.BaseInfoRelationalDto;
import com.nicico.committee.mapper.BaseInfoMapper;
import com.nicico.committee.repository.BaseInfoRepository;
import com.nicico.copper.common.domain.criteria.NICICOCriteria;
import com.nicico.copper.common.domain.criteria.SearchUtil;
import com.nicico.copper.common.dto.grid.TotalResponse;
import com.nicico.copper.common.dto.search.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseInfoService {

    private final BaseInfoRepository baseInfoRepository;
    private final BaseInfoMapper baseInfoMapper;

    @Transactional(readOnly = true)
    public BaseInfoRelationalDto.BaseInfoInfoDTO get(String id) {
        return baseInfoMapper.toDto(baseInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("BaseInfo not found")));
    }

    @Transactional(readOnly = true)
    public List<BaseInfoRelationalDto.BaseInfoInfoDTO> list() {
        return baseInfoMapper.toDto(baseInfoRepository.findAll());
    }

    @Transactional
    public BaseInfoRelationalDto.BaseInfoInfoDTO create(BaseInfoRelationalDto.BaseInfoCreateDTO request) {
        return baseInfoMapper.toDto(baseInfoRepository.save(baseInfoMapper.toEntity(request)));
    }

    @Transactional
    public BaseInfoRelationalDto.BaseInfoInfoDTO update(BaseInfoRelationalDto.BaseInfoUpdateDTO request) {
        return baseInfoMapper.toDto(baseInfoRepository.save(baseInfoMapper.toEntity(request)));
    }

    @Transactional
    public void delete(String id) {
        baseInfoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public TotalResponse<BaseInfoRelationalDto.BaseInfoInfoDTO> search(NICICOCriteria criteria) {
        return SearchUtil.search(baseInfoRepository, criteria, baseInfoMapper::toDto);
    }
    @Transactional(readOnly = true)
    public SearchDTO.SearchRs<BaseInfoRelationalDto.BaseInfoInfoDTO> search(SearchDTO.SearchRq query) {
        return SearchUtil.search(baseInfoRepository, query, baseInfoMapper::toDto);
    }
}
