package com.siscon.employee_service.infrastructure.adapter;

import com.siscon.employee_service.domain.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryAdapter {
    private final EmployeeJpaRepository repository;

    public EmployeeRepositoryAdapter(EmployeeJpaRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Employee> saveAll(List<Employee> employees) {
        return repository.saveAll(employees);
    }

    public Employee save(Employee emp) {
        return repository.save(emp);
    }

}
