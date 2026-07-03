package com.nicico.committee.mapper;

import com.nicico.committee.dto.BaseInfoRelationalDto;
import com.nicico.committee.entities.BaseInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BaseInfoMapper {

    BaseInfoRelationalDto.BaseInfoInfoDTO toDto(BaseInfo baseInfo);

    List<BaseInfoRelationalDto.BaseInfoInfoDTO> toDto(List<BaseInfo> baseInfos);

    @Mappings({
            @Mapping(target = "parent", ignore = true)
    })
    BaseInfo toEntity(BaseInfoRelationalDto.BaseInfoCreateDTO create);

    @Mappings({
            @Mapping(target = "parent", ignore = true)
    })
    BaseInfo toEntity(BaseInfoRelationalDto.BaseInfoUpdateDTO update);
}
