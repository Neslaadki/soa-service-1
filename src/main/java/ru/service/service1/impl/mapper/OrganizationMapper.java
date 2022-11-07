package ru.service.service1.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
import ru.service.service1.db.data.Organization;
import ru.service.service1.impl.config.MapperConfig;

@Mapper(config = MapperConfig.class)
public interface OrganizationMapper {

    OrganizationResponseDto toOrganizationResponseDto(Organization organization);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "creationDate")
    Organization toOrganization(OrganizationRequestDto organizationRequestDto);
}
