package com.siscon.employee_service.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private String position;

}