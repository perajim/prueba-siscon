package com.siscon.employee_service.application.service;

import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import com.siscon.employee_service.infrastructure.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveEmployeeService {
    private final EmployeeRepositoryAdapter repository;

    public EmployeeResponse execute(EmployeeRequest request) {
        Employee entity = Mapper.toEntity(request);
        Employee saved = repository.save(entity);
        return Mapper.toResponse(saved);
    }
}
