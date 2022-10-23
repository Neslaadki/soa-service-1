package ru.service.service1.impl.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.service.service1.db.data.Address;
import ru.service.service1.db.data.Coordinates;
import ru.service.service1.db.data.Organization;
import ru.service.service1.db.data.OrganizationType;
import ru.service.service1.db.repository.OrganizationRepository;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

    @Mock
    OrganizationRepository organizationRepository;

    @Mock
    List<Organization> list;

    @Mock
    Map<OrganizationType, Long> map;
    @InjectMocks
    OrganizationServiceImpl organizationService;

    @BeforeEach
    void init() {
        list = getExampleListOfOrganizations();
        map = getExampleMapOfOrganizationTypeAndCount();
    }

    @Test
    void getMapWithOrganizationTypeAndCountOfOrganizations() {
        Mockito.when(organizationRepository.findAll()).thenReturn(list);

        final var actual = organizationService.getMapWithOrganizationTypeAndCountOfOrganizations();

        Assertions.assertEquals(map, actual);
    }



    private List<Organization> getExampleListOfOrganizations(){
        return List.of(
                Organization.builder()
                        .id(1L)
                        .name("1")
                        .type(OrganizationType.PUBLIC)
                        .creationDate(ZonedDateTime.now())
                        .annualTurnover(10f)
                        .coordinates(Coordinates.builder().x(10).y(20).build())
                        .employeesCount(100)
                        .officialAddress(Address.builder().street("Street").zipCode("180000").build())
                        .build(),
                Organization.builder()
                        .id(2L)
                        .name("2")
                        .type(OrganizationType.PUBLIC)
                        .creationDate(ZonedDateTime.now())
                        .annualTurnover(11f)
                        .coordinates(Coordinates.builder().x(20).y(20).build())
                        .employeesCount(111)
                        .officialAddress(Address.builder().street("Street").zipCode("180100").build())
                        .build(),
                Organization.builder()
                        .id(3L)
                        .name("3")
                        .type(OrganizationType.COMMERCIAL)
                        .creationDate(ZonedDateTime.now())
                        .annualTurnover(10f)
                        .coordinates(Coordinates.builder().x(10).y(20).build())
                        .employeesCount(100)
                        .officialAddress(Address.builder().street("Street").zipCode("180000").build())
                        .build()
        );
    }

    private Map<OrganizationType, Long> getExampleMapOfOrganizationTypeAndCount() {
        var map = new HashMap<OrganizationType, Long>();

        map.put(OrganizationType.PUBLIC, 2L);
        map.put(OrganizationType.COMMERCIAL, 1L);

        return map;
    }
}