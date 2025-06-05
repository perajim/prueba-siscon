package com.siscon.employee_service;

import com.siscon.employee_service.application.service.DeleteEmployeeService;
import com.siscon.employee_service.delivery.api.dto.DeleteEmployeeResponse;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.domain.model.exception.EmployeeNotFoundException;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class DeleteEmployeeServiceTest {

    private EmployeeRepositoryAdapter repository;
    private DeleteEmployeeService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepositoryAdapter.class);
        service = new DeleteEmployeeService(repository);
    }

    @Test
    void shouldDeleteEmployeeSuccessfully() {
        // Arrange
        long employeeId = 1L;
        Employee dummyEmployee = Employee.builder()
                .id(employeeId)
                .firstName("Juan")
                .lastName("Pérez")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();

        when(repository.findById(employeeId)).thenReturn(Optional.of(dummyEmployee));
        doNothing().when(repository).deleteById(employeeId);

        // Act
        DeleteEmployeeResponse response = service.execute(employeeId);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getEmployeeId()).isEqualTo(employeeId);
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Employee deleted successfully");
        assertThat(response.getTimestamp()).isNotNull();

        verify(repository).findById(employeeId);
        verify(repository).deleteById(employeeId);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        // Arrange
        long employeeId = 42L;
        when(repository.findById(employeeId)).thenReturn(Optional.empty());
        System.out.println();
        // Act & Assert
        assertThatThrownBy(() -> service.execute(employeeId))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Employee not found with id: 42");

        verify(repository, never()).deleteById(anyLong());
    }
}
