package com.siscon.employee_service.domain.model.exception;

import java.util.List;

public class EmployeeNotValidException  extends RuntimeException {
    public EmployeeNotValidException(List<String> fields) {
        super("Employee fields required " + String.join(", ", fields));
    }
}