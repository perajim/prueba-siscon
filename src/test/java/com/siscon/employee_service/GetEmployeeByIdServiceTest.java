package com.siscon.employee_service;

import com.siscon.employee_service.application.service.GetEmployeeByIdService;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
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

class GetEmployeeByIdServiceTest {

    private EmployeeRepositoryAdapter repository;
    private GetEmployeeByIdService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepositoryAdapter.class);
        service = new GetEmployeeByIdService(repository);
    }

    @Test
    void shouldReturnEmployeeResponseWhenEmployeeExists() {
        // Arrange
        Long id = 1L;
        Employee employee = Employee.builder()
                .id(id)
                .firstName("Juan")
                .middleName(null)
                .lastName("Pérez")
                .secondLastName(null)
                .age(35)
                .gender("M")
                .birthDate(LocalDate.of(1989, 6, 20))
                .position("Developer")
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        EmployeeResponse response = service.execute(id);

        // Assert
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getFirstName()).contains("Juan");
        assertThat(response.getAge()).isEqualTo(35);
        assertThat(response.getPosition()).isEqualTo("Developer");

        verify(repository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {
        // Arrange
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.execute(id))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining(String.valueOf(id));

        verify(repository).findById(id);
    }
}
