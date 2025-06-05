package com.siscon.employee_service.application.service;

import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.delivery.api.dto.EmployeeUpdateRequest;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.domain.model.exception.EmployeeNotFoundException;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import com.siscon.employee_service.infrastructure.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateEmployeeService {
    private final EmployeeRepositoryAdapter repository;

    public EmployeeResponse execute(Long id, EmployeeUpdateRequest request) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        if (request.getFirstName() != null) employee.setFirstName(request.getFirstName());
        if (request.getMiddleName() != null) employee.setMiddleName(request.getMiddleName());
        if (request.getLastName() != null) employee.setLastName(request.getLastName());
        if (request.getSecondLastName() != null) employee.setSecondLastName(request.getSecondLastName());
        if (request.getAge() != null) employee.setAge(request.getAge());
        if (request.getGender() != null) employee.setGender(request.getGender());
        if (request.getBirthDate() != null) employee.setBirthDate(request.getBirthDate());
        if (request.getPosition() != null) employee.setPosition(request.getPosition());
        return Mapper.toResponse(repository.save(employee));
    }
}
