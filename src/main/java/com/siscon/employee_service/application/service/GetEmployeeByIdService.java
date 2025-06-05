package com.siscon.employee_service.application.service;

import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.domain.model.exception.EmployeeNotFoundException;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import com.siscon.employee_service.infrastructure.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetEmployeeByIdService {
    private final EmployeeRepositoryAdapter repository;

    public EmployeeResponse execute(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return Mapper.toResponse(employee);
    }
}
