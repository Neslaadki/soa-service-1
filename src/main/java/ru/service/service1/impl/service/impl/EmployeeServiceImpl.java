package ru.service.service1.impl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.service.service1.api.dto.EmployeeRq;
import ru.service.service1.api.dto.EmployeeRs;
import ru.service.service1.db.data.Employee;
import ru.service.service1.db.data.Organization;
import ru.service.service1.db.repository.EmployeeRepository;
import ru.service.service1.db.repository.OrganizationRepository;
import ru.service.service1.impl.exception.NotFoundException;
import ru.service.service1.impl.mapper.EmployeeMapper;
import ru.service.service1.impl.service.EmployeeService;
import ru.service.service1.impl.service.OrganizationService;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private final OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public EmployeeRs create(EmployeeRq employeeRq, Long organization_id) {
        Employee employee = employeeMapper.toEmployee(employeeRq, organization_id);
        Employee saved = employeeRepository.save(employee);
        var count = employeeRepository.findEmployeeByOrganizationId(organization_id).size();
        log.info("Saved employee : {}", saved);
        var organization = organizationRepository.findById(organization_id);
        if (organization.isPresent()){
            organization.get().setEmployeesCount(count);
            organizationRepository.save(organization.get());
            return employeeMapper.toEmployeeRs(saved);
        }else {
            throw new NotFoundException(Organization.class, organization_id);
        }
    }

    @Override
    @Transactional
    public void deleteAllEmployee(Long organization_id) {

        employeeRepository.deleteAllByOrganizationId(organization_id);

    }
}
