package ru.service.service1.impl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.service.service1.api.controller.EmployeeControllerV1;
import ru.service.service1.api.dto.EmployeeRq;
import ru.service.service1.api.dto.EmployeeRs;
import ru.service.service1.impl.service.EmployeeService;

@RestController
@RequiredArgsConstructor
public class EmployeeControllerV1Impl implements EmployeeControllerV1 {

    private final EmployeeService employeeService;

    @Override
    public EmployeeRs create(EmployeeRq employeeRq, Long id) {
        return employeeService.create(employeeRq, id);
    }

    @Override
    public ResponseEntity<?> deleteAllEmployee(Long id) {

        employeeService.deleteAllEmployee(id);

        return ResponseEntity.ok().build();
    }
}
