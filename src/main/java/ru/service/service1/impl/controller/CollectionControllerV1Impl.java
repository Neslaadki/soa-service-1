package ru.service.service1.impl.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.service.service1.api.controller.CollectionControllerV1;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.OrganizationType;
import ru.service.service1.impl.service.OrganizationService;

@RestController
@RequiredArgsConstructor
public class CollectionControllerV1Impl implements CollectionControllerV1 {

  private final OrganizationService organizationService;

  @Override
  public OrganizationResponseDto create(
      OrganizationRequestDto organizationRequestDto) {
    return organizationService.create(
            organizationRequestDto);
  }

  @Override
  public Page<OrganizationResponseDto> getAll(Pageable pageable) {
    return organizationService.findAll(pageable);
  }

  @Override
  public OrganizationResponseDto getById(Long id) {
    return organizationService.findById(id);
  }

  @Override
  public ResponseEntity<?> deleteById(Long id) {
    organizationService.deleteById(id);
    return ResponseEntity.ok(HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<?> getByType(OrganizationType organizationType) {
    return ResponseEntity.ok(organizationService.deleteWithType(organizationType));
  }

  @Override
  public List<OrganizationResponseDto> getByEmployeeCount(long count) {
    return organizationService.findByEmployeeCountBiggestThan(count);
  }

  @Override
  public OrganizationResponseDto example() {
    Coordinates coordinates = Coordinates.builder().x(10).y(20).build();
    Address address = Address.builder().street("Street").zipCode("184250").build();
    OrganizationResponseDto organizationResponseDto =
        OrganizationResponseDto.builder()
            .id(10l)
            .annualTurnover(10f)
            .creationDate(LocalDateTime.now())
            .coordinates(coordinates)
            .officialAddress(address)
            .employeesCount(1000L)
            .name("OrganizationName")
            .type(OrganizationType.COMMERCIAL)
            .build();
    return organizationResponseDto;
  }

  @GetMapping(value = "/ex", produces = "application/xml")
  public Example ex() {
    Example example = new Example();
    example.setName("Example");
    example.setAge(10);
    return example;
  }
}
