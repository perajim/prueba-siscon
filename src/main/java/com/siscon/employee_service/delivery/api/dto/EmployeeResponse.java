package com.siscon.employee_service.delivery.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private String position;
}
