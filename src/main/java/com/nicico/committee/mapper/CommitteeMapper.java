package com.nicico.committee.mapper;

import com.nicico.committee.dto.CommitteeRelationalDto;
import com.nicico.committee.entities.Committee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BaseInfoMapper.class)
public interface CommitteeMapper extends DefaultMapper<CommitteeRelationalDto.CommitteeInfoDTO,
        CommitteeRelationalDto.CommitteeUpdateDTO, CommitteeRelationalDto.CommitteeCreateDTO, Committee> {

    @Override
    CommitteeRelationalDto.CommitteeInfoDTO toDto(Committee entity);

    @Named("toIdDto")
    CommitteeRelationalDto.CommitteeIdDTO toIdDto(Committee entity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "businessDomainId", source = "businessDomain.id")
    @Mapping(target = "subjectCategoryId", source = "subjectCategory.id")
    @Mapping(target = "parentCommitteeId", source = "parentCommittee.id")
    @Mapping(target = "systemOwnerId", source = "systemOwner.id")
    @Mapping(target = "committeeLocationId", source = "committeeLocation.id")
    @Mapping(target = "committeeLevelId", source = "committeeLevel.id")
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "businessDomain", ignore = true)
    @Mapping(target = "subjectCategory", ignore = true)
    @Mapping(target = "parentCommittee", ignore = true)
    @Mapping(target = "systemOwner", ignore = true)
    @Mapping(target = "committeeLocation", ignore = true)
    @Mapping(target = "committeeLevel", ignore = true)
    Committee toEntityForCreate(CommitteeRelationalDto.CommitteeCreateDTO createDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "businessDomainId", source = "businessDomain.id")
    @Mapping(target = "subjectCategoryId", source = "subjectCategory.id")
    @Mapping(target = "parentCommitteeId", source = "parentCommittee.id")
    @Mapping(target = "systemOwnerId", source = "systemOwner.id")
    @Mapping(target = "committeeLocationId", source = "committeeLocation.id")
    @Mapping(target = "committeeLevelId", source = "committeeLevel.id")
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "businessDomain", ignore = true)
    @Mapping(target = "subjectCategory", ignore = true)
    @Mapping(target = "parentCommittee", ignore = true)
    @Mapping(target = "systemOwner", ignore = true)
    @Mapping(target = "committeeLocation", ignore = true)
    @Mapping(target = "committeeLevel", ignore = true)
    Committee toEntityForUpdate(CommitteeRelationalDto.CommitteeUpdateDTO updateDto);
}