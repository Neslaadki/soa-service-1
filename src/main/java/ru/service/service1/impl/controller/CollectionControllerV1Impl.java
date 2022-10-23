package ru.service.service1.impl.controller;

import java.time.ZonedDateTime;
import java.util.List;
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

@RestController
public class CollectionControllerV1Impl implements CollectionControllerV1 {

  @Override
  public ResponseEntity<OrganizationRequestDto> create(
      OrganizationRequestDto organizationRequestDto) {

    System.out.println(organizationRequestDto.toString());

    return new ResponseEntity<>(organizationRequestDto, HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<Page<OrganizationResponseDto>> getAll(Pageable pageable) {
    return null;
  }

  @Override
  public OrganizationResponseDto getById(Long id) {
    return null;
  }

  @Override
  public ResponseEntity deleteById(Long id) {
    return null;
  }

  @Override
  public ResponseEntity getByType(OrganizationType organizationType) {
    return null;
  }

  @Override
  public ResponseEntity getByEmployeeCount(long count) {
    return null;
  }

  @Override
  public ResponseEntity<OrganizationResponseDto> example() {
    Coordinates coordinates = Coordinates.builder().x(10).y(20).build();
    Address address = Address.builder().street("Street").zipCode("184250").build();
    OrganizationResponseDto organizationResponseDto =
        OrganizationResponseDto.builder()
            .id(10l)
            .annualTurnover(10f)
            .creationDate(ZonedDateTime.now())
            .coordinates(coordinates)
            .officialAddress(address)
            .employeesCount(1000L)
            .name("OrganizationName")
            .type(OrganizationType.COMMERCIAL)
            .build();
    return new ResponseEntity<>(organizationResponseDto, HttpStatus.ACCEPTED);
  }

  @GetMapping(value = "/ex", produces = "application/xml")
  public Example ex() {
    Example example = new Example();
    example.setName("Example");
    example.setAge(10);
    return example;
  }
}
