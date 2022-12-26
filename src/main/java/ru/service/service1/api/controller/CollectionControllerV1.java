package ru.service.service1.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.service.service1.api.dto.EmployeeCountRs;
import ru.service.service1.api.dto.OrganizationRq;
import ru.service.service1.api.dto.OrganizationRs;
import ru.service.service1.api.dto.OrganizationUpdateRq;
import ru.service.service1.db.data.OrganizationType;

@CrossOrigin
@RequestMapping(value = "/api/v1/organizations")
public interface CollectionControllerV1 {

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    OrganizationRs create(
            @Validated @RequestBody OrganizationRq organizationRq);

    @GetMapping
    Page<OrganizationRs> getAll(Pageable pageable);

    @GetMapping(value = "/{organization_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    OrganizationRs getById(@PathVariable("organization_id") Long id);

    @DeleteMapping("/{organization_id}")
    ResponseEntity<?> deleteById(@PathVariable("organization_id") Long id);

    @GetMapping("/types/{type}")
    ResponseEntity<?> deleteByType(@PathVariable("type") OrganizationType organizationType);

    @GetMapping(value = "/employees_counts/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<OrganizationRs> getByEmployeeCount(@PathVariable("count") long count);

    @PutMapping
    OrganizationRs update(@Validated @RequestBody OrganizationUpdateRq organizationUpdateRq);

    @GetMapping(value = "/groups/types", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<OrganizationType, Long> groupEmployeeCountByType();

}
