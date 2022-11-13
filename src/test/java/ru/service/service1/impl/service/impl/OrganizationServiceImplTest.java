package ru.service.service1.impl.service.impl;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
import org.yaml.snakeyaml.events.Event.ID;
import ru.service.service1.api.dto.OrganizationRequestDto;
import ru.service.service1.api.dto.OrganizationResponseDto;
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

  private OrganizationRequestDto organizationRequestDto;
  private OrganizationResponseDto exampleOfOrganizationResponseDto;

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
    organizationRequestDto = getExampleOfOrganizationRequestDto();
    exampleOfOrganizationResponseDto = getExampleOfOrganizationResponseDto();
    organization = exampleOfOrganization.get();
  }

  @Test
  void findById_ShouldReturnOrganizationResponseDto_ifExists() {
    final var ID = 1L;

    Mockito.when(organizationRepository.findById(ID)).thenReturn(exampleOfOrganization);
    Mockito.when(organizationMapper.toOrganizationResponseDto(organization))
        .thenReturn(exampleOfOrganizationResponseDto);

    var actual = organizationService.findById(ID);
    Assertions.assertEquals(exampleOfOrganizationResponseDto, actual);
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
        new PageImpl<>(List.of(exampleOfOrganizationResponseDto));

    Mockito.when(organizationRepository.findAll(pageable))
        .thenReturn(new PageImpl<>(organizationList));
    Mockito.when(organizationMapper.toOrganizationResponseDto(organization))
        .thenReturn(exampleOfOrganizationResponseDto);

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

    Mockito.when(organizationMapper.toOrganization(organizationRequestDto)).thenReturn(organization);
    Mockito.when(organizationRepository.save(organization)).thenReturn(organization);
    Mockito.when(organizationMapper.toOrganizationResponseDto(organization)).thenReturn(exampleOfOrganizationResponseDto);
    Mockito.when(organizationRepository.findById(organization.getId())).thenReturn(Optional.of(organization));
    Mockito.when(organizationService.findById(exampleOfOrganizationResponseDto.getId())).thenReturn(exampleOfOrganizationResponseDto);

    var actual = organizationService.create(organizationRequestDto);

    Assertions.assertEquals(exampleOfOrganizationResponseDto, actual);
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

  private OrganizationResponseDto getExampleOfOrganizationResponseDto() {
    return OrganizationResponseDto.builder()
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

  private OrganizationRequestDto getExampleOfOrganizationRequestDto() {
    return OrganizationRequestDto.builder()
            .name("1")
            .type(OrganizationType.PUBLIC)
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            .employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build();
  }

  private List<OrganizationResponseDto> getExampleListOfOrganizationResponseDtos() {
    return List.of(
        OrganizationResponseDto.builder()
            .id(1L)
            .name("1")
            .type(OrganizationType.PUBLIC)
            .creationDate(LocalDateTime.now())
            .annualTurnover(10f)
            .coordinates(Coordinates.builder().x(10).y(20).build())
            .employeesCount(100)
            .officialAddress(Address.builder().street("Street").zipCode("180000").build())
            .build(),
        OrganizationResponseDto.builder()
            .id(2L)
            .name("2")
            .type(OrganizationType.PUBLIC)
            .creationDate(LocalDateTime.now())
            .annualTurnover(11f)
            .coordinates(Coordinates.builder().x(20).y(20).build())
            .employeesCount(111)
            .officialAddress(Address.builder().street("Street").zipCode("180100").build())
            .build(),
        OrganizationResponseDto.builder()
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
