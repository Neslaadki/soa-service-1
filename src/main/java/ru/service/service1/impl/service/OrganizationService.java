package ru.service.service1.impl.service;

import java.util.List;
import java.util.Optional;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
import ru.service.service1.db.data.Organization;
import ru.service.service1.db.data.OrganizationType;

public interface OrganizationService {

    OrganizationResponseDto findById(Long id);

    Page<OrganizationResponseDto> findAll(Pageable pageable);

    OrganizationResponseDto deleteById(Long id);

    OrganizationResponseDto create(OrganizationRequestDto organizationRequestDto);

    List<OrganizationResponseDto> findByType(OrganizationType organizationType);

    List<OrganizationResponseDto> findByEmployeeCountBiggestThan(long count);

}
