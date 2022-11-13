package ru.service.service1.impl.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.service.service1.api.dto.OrganizationRq;
import ru.service.service1.api.dto.OrganizationRs;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.Organization;
import ru.service.service1.db.data.OrganizationType;
import ru.service.service1.db.repository.OrganizationRepository;
import ru.service.service1.impl.exception.NotFoundException;
import ru.service.service1.impl.mapper.OrganizationMapper;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {
  private Organization organization;
  private Optional<Organization> exampleOfOrganization;

  private OrganizationRq organizationRq;
  private OrganizationRs exampleOfOrganizationRs;

  @Mock OrganizationRepository organizationRepository;

  @Mock OrganizationMapper organizationMapper;

  @Mock List<Organization> list;

  @Mock Map<OrganizationType, Long> map;
  @InjectMocks OrganizationServiceImpl organizationService;

  @BeforeEach
  void init() {
    list = getExampleListOfOrganizations();
    map = getExampleMapOfOrganizationTypeAndCount();
    exampleOfOrganization = getExampleOptionalOfOrganization();
    organizationRq = getExampleOfOrganizationRequestDto();
    exampleOfOrganizationRs = getExampleOfOrganizationResponseDto();
    organization = exampleOfOrganization.get();
  }

  @Test
  void findById_ShouldReturnOrganizationResponseDto_ifExists() {
    final var ID = 1L;

    Mockito.when(organizationRepository.findById(ID)).thenReturn(exampleOfOrganization);
    Mockito.when(organizationMapper.toOrganizationResponseDto(organization))
        .thenReturn(exampleOfOrganizationRs);

    var actual = organizationService.findById(ID);
    Assertions.assertEquals(exampleOfOrganizationRs, actual);
  }

  @Test
  void findById_ShouldReturnOrganizationResponseDto_ifNotExists() {
    final var ID = 2L;

    Mockito.when(organizationRepository.findById(ID)).thenReturn(exampleOfOrganization);
    Mockito.when(organizationMapper.toOrganizationResponseDto(organization))
        .thenThrow(NotFoundException.class);

    Assertions.assertThrows(NotFoundException.class, () -> organizationService.findById(ID));
  }

  @Test
  void findAll_ShouldReturnPageOfOrganizationResponseDto() {
    final var pageable = PageRequest.of(1, 1);
    final var organizationList = List.of(organization);
    final var exampleListOfOrganizationResponseDtos =
        new PageImpl<>(List.of(exampleOfOrganizationRs));

    Mockito.when(organizationRepository.findAll(pageable))
        .thenReturn(new PageImpl<>(organizationList));
    Mockito.when(organizationMapper.toOrganizationResponseDto(organization))
        .thenReturn(exampleOfOrganizationRs);

    final var actual = organizationService.findAll(pageable);
    Assertions.assertEquals(exampleListOfOrganizationResponseDtos, actual);
  }

  @Test
  void getMapWithOrganizationTypeAndCountOfOrganizations() {
    Mockito.when(organizationRepository.findAll()).thenReturn(list);

    final var actual = organizationService.getMapWithOrganizationTypeAndCountOfOrganizations();

    Assertions.assertEquals(map, actual);
  }

  @Test
  void create_ShouldReturnOrganizationResponseDto_WhenCreated() {

    Mockito.when(organizationMapper.toOrganization(organizationRq)).thenReturn(organization);
    Mockito.when(organizationRepository.save(organization)).thenReturn(organization);
    Mockito.when(organizationMapper.toOrganizationResponseDto(organization)).thenReturn(exampleOfOrganizationRs);
    Mockito.when(organizationRepository.findById(organization.getId())).thenReturn(Optional.of(organization));
    Mockito.when(organizationService.findById(exampleOfOrganizationRs.getId())).thenReturn(exampleOfOrganizationRs);

    var actual = organizationService.create(organizationRq);

    Assertions.assertEquals(exampleOfOrganizationRs, actual);
  }

  private List<Organization> getExampleListOfOrganizations() {
    return List.of(
        Organization.builder()
            .id(1L)
            .name("1")
            .type(OrganizationType.PUBLIC)
            .creationDate(LocalDateTime.now())
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            .employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build(),
        Organization.builder()
            .id(2L)
            .name("2")
            .type(OrganizationType.PUBLIC)
            .creationDate(LocalDateTime.now())
            .annualTurnover(11f)
            .coordinates(Coordinates.builder().x(20).y(20).build())
            .employeesCount(111)
            .officialAddress(Address.builder().street("Street").zipCode("180100").build())
            .build(),
        Organization.builder()
            .id(3L)
            .name("3")
            .type(OrganizationType.COMMERCIAL)
            .creationDate(LocalDateTime.now())
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            .employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build());
  }

  private Map<OrganizationType, Long> getExampleMapOfOrganizationTypeAndCount() {
    var map = new HashMap<OrganizationType, Long>();

    map.put(OrganizationType.PUBLIC, 2L);
    map.put(OrganizationType.COMMERCIAL, 1L);

    return map;
  }

  private Optional<Organization> getExampleOptionalOfOrganization() {
    return Optional.of(
        Organization.builder()
            .id(1L)
            .name("1")
            .type(OrganizationType.PUBLIC)
            .creationDate(LocalDateTime.now())
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            .employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build());
  }

  private OrganizationRs getExampleOfOrganizationResponseDto() {
    return OrganizationRs.builder()
        .id(1L)
        .name("1")
        .type(OrganizationType.PUBLIC)
        .creationDate(LocalDateTime.now())
        .annualTurnover(10f)
        .coordinates(Coordinates.builder().x(10).y(20).build())
        .employeesCount(100)
        .officialAddress(Address.builder().street("Street").zipCode("180000").build())
        .build();
  }

  private OrganizationRq getExampleOfOrganizationRequestDto() {
    return OrganizationRq.builder()
            .name("1")
            .type(OrganizationType.PUBLIC)
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            //.employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build();
  }

  private List<OrganizationRs> getExampleListOfOrganizationResponseDtos() {
    return List.of(
        OrganizationRs.builder()
            .id(1L)
            .name("1")
            .type(OrganizationType.PUBLIC)
            .creationDate(LocalDateTime.now())
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            .employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build(),
        OrganizationRs.builder()
            .id(2L)
            .name("2")
            .type(OrganizationType.PUBLIC)
            .creationDate(LocalDateTime.now())
            .annualTurnover(11f)
            .coordinates(Coordinates.builder().x(20).y(20).build())
            .employeesCount(111)
            .officialAddress(Address.builder().street("Street").zipCode("180100").build())
            .build(),
        OrganizationRs.builder()
            .id(3L)
            .name("3")
            .type(OrganizationType.COMMERCIAL)
            .creationDate(LocalDateTime.now())
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            .employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build());
  }
}
