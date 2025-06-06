package com.siscon.employee_service;

import com.siscon.employee_service.application.service.UpdateEmployeeService;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.delivery.api.dto.EmployeeUpdateRequest;
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

class UpdateEmployeeServiceTest {

    private EmployeeRepositoryAdapter repository;
    private UpdateEmployeeService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepositoryAdapter.class);
        service = new UpdateEmployeeService(repository);
    }

    @Test
    void shouldUpdateEmployeeWithGivenFields() {
        // Arrange
        Long employeeId = 1L;
        Employee existing = Employee.builder()
                .id(employeeId)
                .firstName("Carlos")
                .middleName("Luis")
                .lastName("Ramírez")
                .secondLastName("Gómez")
                .age(40)
                .gender("M")
                .birthDate(LocalDate.of(1985, 2, 15))
                .position("Engineer")
                .build();

        EmployeeUpdateRequest updateRequest = new EmployeeUpdateRequest();
        updateRequest.setFirstName("Carlos Eduardo");
        updateRequest.setAge(41); // only update two fields

        Employee updated = Employee.builder()
                .id(employeeId)
                .firstName("Carlos Eduardo")
                .middleName("Luis")
                .lastName("Ramírez")
                .secondLastName("Gómez")
                .age(41)
                .gender("M")
                .birthDate(LocalDate.of(1985, 2, 15))
                .position("Engineer")
                .build();

        when(repository.findById(employeeId)).thenReturn(Optional.of(existing));
        when(repository.save(any(Employee.class))).thenReturn(updated);

        // Act
        EmployeeResponse response = service.execute(employeeId, updateRequest);

        // Assert
        assertThat(response.getId()).isEqualTo(employeeId);
        assertThat(response.getFirstName()).contains("Carlos Eduardo");
        assertThat(response.getAge()).isEqualTo(41);

        verify(repository).findById(employeeId);
        verify(repository).save(existing); // employee is modified in-place
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        // Arrange
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.execute(id, new EmployeeUpdateRequest()))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("Employee not found with id: 999");

        verify(repository, never()).save(any());
    }
}
