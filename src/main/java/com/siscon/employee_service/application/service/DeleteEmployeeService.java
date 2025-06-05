package com.siscon.employee_service.application.service;



import com.siscon.employee_service.delivery.api.dto.DeleteEmployeeResponse;
import com.siscon.employee_service.domain.model.exception.EmployeeNotFoundException;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class DeleteEmployeeService {
    private final EmployeeRepositoryAdapter repository;

    public DeleteEmployeeResponse execute(long id) {
        repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        repository.deleteById(id);

        DeleteEmployeeResponse response = new DeleteEmployeeResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Employee deleted successfully",
                id
        );

        return response;
    }
}
