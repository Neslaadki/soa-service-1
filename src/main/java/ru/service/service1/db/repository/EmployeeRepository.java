package ru.service.service1.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.service.service1.db.data.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    void deleteAllByOrganizationId(Long id);

}
