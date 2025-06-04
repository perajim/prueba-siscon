package com.siscon.employee_service.application.service;

import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import com.siscon.employee_service.shared.dto.EmployeeRequest;
import com.siscon.employee_service.shared.dto.EmployeeResponse;
import com.siscon.employee_service.shared.exception.EmployeeNotFoundException;
import com.siscon.employee_service.shared.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepositoryAdapter repository;

    public EmployeeService(EmployeeRepositoryAdapter repository) {
        this.repository = repository;
    }

    public List<EmployeeResponse> getAllEmployees() {
        return repository.findAll().stream().map(Mapper::toResponse).toList();
    }

    public void deleteEmployee(Long id) {
        repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repository.deleteById(id);
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest req) {
        Employee emp = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        emp.setFirstName(req.getFirstName());
        emp.setMiddleName(req.getMiddleName());
        emp.setLastName(req.getLastName());
        emp.setSecondLastName(req.getSecondLastName());
        emp.setAge(req.getAge());
        emp.setGender(req.getGender());
        emp.setBirthDate(req.getBirthDate());
        emp.setPosition(req.getPosition());
        return Mapper.toResponse(repository.save(emp));
    }

    public List<EmployeeResponse> saveEmployees(List<EmployeeRequest> requests) {
        var entities = requests.stream().map(Mapper::toEntity).toList();
        return repository.saveAll(entities).stream().map(Mapper::toResponse).toList();
    }
}
