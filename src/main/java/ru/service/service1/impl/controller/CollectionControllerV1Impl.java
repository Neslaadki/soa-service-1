package ru.service.service1.impl.controller;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.service.service1.api.controller.CollectionControllerV1;
import ru.service.service1.api.dto.OrganizationRq;
import ru.service.service1.api.dto.OrganizationRs;
import ru.service.service1.api.dto.OrganizationUpdateRq;
import ru.service.service1.db.data.OrganizationType;
import ru.service.service1.impl.service.OrganizationService;

@RestController
@RequiredArgsConstructor
public class CollectionControllerV1Impl implements CollectionControllerV1 {

    private final OrganizationService organizationService;

    @Override
    public OrganizationRs create(
            OrganizationRq organizationRq) {
        return organizationService.create(
                organizationRq);
    }

    @Override
    public Page<OrganizationRs> getAll(Pageable pageable) {
        return organizationService.findAll(pageable);
    }

    @Override
    public OrganizationRs getById(Long id) {
        return organizationService.findById(id);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        organizationService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<?> deleteByType(OrganizationType organizationType) {
        return ResponseEntity.ok(organizationService.deleteWithType(organizationType));
    }

    @Override
    public List<OrganizationRs> getByEmployeeCount(long count) {
        return organizationService.findByEmployeeCountBiggestThan(count);
    }

    @Override
    public OrganizationRs update(OrganizationUpdateRq organizationUpdateRq) {
        return organizationService.update(organizationUpdateRq);
    }

    @Override
    public Map<OrganizationType, Long> groupEmployeeCountByType() {
        return organizationService.getMapWithOrganizationTypeAndCountOfOrganizations();
    }


}
