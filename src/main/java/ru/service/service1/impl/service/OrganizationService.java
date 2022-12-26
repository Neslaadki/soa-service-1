package ru.service.service1.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.service.service1.api.dto.OrganizationRq;
import ru.service.service1.api.dto.OrganizationRs;
import ru.service.service1.api.dto.OrganizationUpdateRq;
import ru.service.service1.db.data.OrganizationType;

public interface OrganizationService {

    OrganizationRs findById(Long id);

    Page<OrganizationRs> findAll(Pageable pageable);

    OrganizationRs deleteById(Long id);
    List<OrganizationRs> deleteWithType(OrganizationType organizationType);

    OrganizationRs create(OrganizationRq organizationRq);

    List<OrganizationRs> findByEmployeeCountBiggestThan(long count);

    Map<OrganizationType, Long> getMapWithOrganizationTypeAndCountOfOrganizations();

    OrganizationRs update(OrganizationUpdateRq organizationUpdateRq);

}
