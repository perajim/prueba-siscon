package com.siscon.employee_service.shared.util;

import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.shared.dto.EmployeeRequest;
import com.siscon.employee_service.shared.dto.EmployeeResponse;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapper {
    public static Employee toEntity(EmployeeRequest req) {
        return Employee.builder()
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName())
                .lastName(req.getLastName())
                .secondLastName(req.getSecondLastName())
                .age(req.getAge())
                .gender(req.getGender())
                .birthDate(req.getBirthDate())
                .position(req.getPosition())
                .build();
    }

    public static EmployeeResponse toResponse(Employee emp) {
        String fullName = Stream.of(emp.getFirstName(), emp.getMiddleName(), emp.getLastName(), emp.getSecondLastName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
        return new EmployeeResponse(emp.getId(), fullName, emp.getAge(), emp.getGender(), emp.getBirthDate(), emp.getPosition());
    }
}
