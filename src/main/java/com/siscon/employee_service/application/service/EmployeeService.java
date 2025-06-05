package com.siscon.employee_service.application.service;

import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import com.siscon.employee_service.shared.dto.DeleteEmployeeResponse;
import com.siscon.employee_service.shared.dto.EmployeeRequest;
import com.siscon.employee_service.shared.dto.EmployeeResponse;
import com.siscon.employee_service.shared.dto.EmployeeUpdateRequest;
import com.siscon.employee_service.shared.exception.EmployeeNotFoundException;
import com.siscon.employee_service.shared.util.Mapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private final EmployeeRepositoryAdapter repository;

    public EmployeeService(EmployeeRepositoryAdapter repository) {
        this.repository = repository;
    }

    public List<EmployeeResponse> getAllEmployees() {
        return repository.findAll().stream().map(Mapper::toResponse).toList();
    }

    public DeleteEmployeeResponse deleteEmployee(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        repository.deleteById(id);

        DeleteEmployeeResponse response = new DeleteEmployeeResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Employee deleted successfully",
                id
        );

        return response;
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request) {
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

    public List<EmployeeResponse> saveEmployees(List<EmployeeRequest> requests) {
        var entities = requests.stream().map(Mapper::toEntity).toList();
        return repository.saveAll(entities).stream().map(Mapper::toResponse).toList();
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return Mapper.toResponse(employee);
    }

    public EmployeeResponse saveEmployee(EmployeeRequest request) {
        Employee entity = Mapper.toEntity(request);
        Employee saved = repository.save(entity);
        return Mapper.toResponse(saved);
    }

}
