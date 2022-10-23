package ru.service.service1.impl.mapper;

import org.mapstruct.Mapper;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
import ru.service.service1.db.data.Organization;

@Mapper
public interface OrganizationMapper {

    OrganizationResponseDto toOrganizationResponseDto(Organization organization);

    Organization toOrganization(OrganizationRequestDto organizationRequestDto);
}
