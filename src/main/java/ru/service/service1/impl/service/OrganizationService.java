package ru.service.service1.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
import ru.service.service1.api.dto.OrganizationRequestDtoForUpdate;
import ru.service.service1.db.data.OrganizationType;

public interface OrganizationService {

    OrganizationResponseDto findById(Long id);

    Page<OrganizationResponseDto> findAll(Pageable pageable);

    OrganizationResponseDto deleteById(Long id);
    List<OrganizationResponseDto> deleteWithType(OrganizationType organizationType);

    OrganizationResponseDto create(OrganizationRequestDto organizationRequestDto);

    List<OrganizationResponseDto> findByEmployeeCountBiggestThan(long count);

    Map<OrganizationType, Long> getMapWithOrganizationTypeAndCountOfOrganizations();

    OrganizationResponseDto updateById(OrganizationRequestDtoForUpdate organizationRequestDtoForUpdate);

}
