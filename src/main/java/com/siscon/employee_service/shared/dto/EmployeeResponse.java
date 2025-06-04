package com.siscon.employee_service.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String fullName;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private String position;
}
