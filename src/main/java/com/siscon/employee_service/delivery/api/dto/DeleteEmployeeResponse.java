package com.siscon.employee_service.delivery.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteEmployeeResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private Long employeeId;
}