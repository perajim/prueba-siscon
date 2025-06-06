package com.siscon.employee_service.infrastructure.mappers;

import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapper {
    public static Employee toEntity(EmployeeRequest req) {
        return Employee.builder()
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName() != null ? req.getMiddleName() : "")
                .lastName(req.getLastName())
                .secondLastName(req.getSecondLastName() != null ? req.getSecondLastName() : "" )
                .age(req.getAge() != null ? req.getAge() : 0)
                .gender(req.getGender() != null ? req.getGender() : "")
                .birthDate(req.getBirthDate())
                .position(req.getPosition() != null ? req.getPosition() : "")
                .build();
    }

    public static EmployeeResponse toResponse(Employee emp) {
        
        return new EmployeeResponse(emp.getId(), emp.getFirstName(),emp.getMiddleName(), emp.getLastName(), emp.getAge(), emp.getGender(), emp.getBirthDate(), emp.getPosition());
    }
}
