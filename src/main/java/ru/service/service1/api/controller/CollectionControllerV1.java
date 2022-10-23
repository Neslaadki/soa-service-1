package ru.service.service1.api.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
import ru.service.service1.db.data.OrganizationType;

@RequestMapping(value = "/api/v1", consumes = "application/xml", produces = "application/xml")
public interface CollectionControllerV1 {

  @PostMapping("/organizations")
  ResponseEntity<OrganizationRequestDto> create(
      @Validated @RequestBody OrganizationRequestDto organizationRequestDto);

  @GetMapping("/organizations")
  ResponseEntity<Page<OrganizationResponseDto>> getAll(Pageable pageable);

  @GetMapping("/organizations/{organization_id}")
  OrganizationResponseDto getById(@PathVariable("organization_id") Long id);

  /*
  put method
   */

  @DeleteMapping("/organizations/{organization_id}")
  ResponseEntity deleteById(@PathVariable("organization_id") Long id);

  @GetMapping("/organizations/types/{type}")
  ResponseEntity getByType(@PathVariable("type") OrganizationType organizationType);

  @GetMapping("/organizations/employees_counts/{count}")
  ResponseEntity getByEmployeeCount(@PathVariable("count") long count);

  @GetMapping("/organizations/example")
  ResponseEntity<OrganizationResponseDto> example();
}
