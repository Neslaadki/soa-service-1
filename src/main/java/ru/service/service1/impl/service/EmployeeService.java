package ru.service.service1.impl.service;


import ru.service.service1.api.dto.EmployeeRq;
import ru.service.service1.api.dto.EmployeeRs;

public interface EmployeeService {

    EmployeeRs create(EmployeeRq employeeRq, Long organization_id);

    void deleteAllEmployee(Long organization_id);

}
