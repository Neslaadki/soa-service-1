package ru.service.service1.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.service.service1.api.dto.EmployeeRq;
import ru.service.service1.api.dto.EmployeeRs;

import javax.validation.Valid;

@RequestMapping(value = "/api/v1/employees")
public interface EmployeeControllerV1 {


    @PostMapping("/hire/{id}")
    EmployeeRs create(@RequestBody @Valid EmployeeRq employeeRq, @PathVariable("id") Long id);

    @DeleteMapping("/fire/all/{id}")
    ResponseEntity<?> deleteAllEmployee(@PathVariable(value = "id") Long id);


}
