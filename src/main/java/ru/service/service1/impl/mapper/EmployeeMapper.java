package ru.service.service1.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.service.service1.api.dto.EmployeeRq;
import ru.service.service1.api.dto.EmployeeRs;
import ru.service.service1.db.data.Employee;
import ru.service.service1.impl.config.MapperConfig;

@Mapper(config = MapperConfig.class)
public interface EmployeeMapper {

    @Mapping(ignore = true, target = "id")
    Employee toEmployee(EmployeeRq employeeRq,  Long organizationId);

    EmployeeRs toEmployeeRs(Employee employee);

}
