package ru.service.service1.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.service.service1.api.dto.OrganizationRq;
import ru.service.service1.api.dto.OrganizationRs;
import ru.service.service1.db.data.Organization;
import ru.service.service1.impl.config.MapperConfig;

@Mapper(config = MapperConfig.class)
public interface OrganizationMapper {

    OrganizationRs toOrganizationResponseDto(Organization organization);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "creationDate")
    @Mapping(ignore = true, target = "employeesCount")
    Organization toOrganization(OrganizationRq organizationRq);
}
