package com.siscon.employee_service.application.service;

import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import com.siscon.employee_service.infrastructure.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveEmployeesService {
    private final EmployeeRepositoryAdapter repository;

    public List<EmployeeResponse> execute(List<EmployeeRequest> requests) {
        var entities = requests.stream().map(Mapper::toEntity).toList();
        return repository.saveAll(entities).stream().map(Mapper::toResponse).toList();
    }
}
