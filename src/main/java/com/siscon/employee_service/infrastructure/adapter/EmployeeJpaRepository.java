package com.siscon.employee_service.infrastructure.adapter;

import com.siscon.employee_service.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {}
