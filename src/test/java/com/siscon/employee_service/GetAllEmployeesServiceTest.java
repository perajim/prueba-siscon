package com.siscon.employee_service;

import com.siscon.employee_service.application.service.GetAllEmployeesService;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllEmployeesServiceTest {

    private EmployeeRepositoryAdapter repository;
    private GetAllEmployeesService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepositoryAdapter.class);
        service = new GetAllEmployeesService(repository);
    }

    @Test
    void shouldReturnListOfEmployees() {
        // Arrange
        Employee emp1 = Employee.builder()
                .id(1L)
                .firstName("Ana")
                .lastName("López")
                .age(30)
                .gender("F")
                .birthDate(LocalDate.of(1994, 1, 15))
                .position("Analyst")
                .build();

        Employee emp2 = Employee.builder()
                .id(2L)
                .firstName("Carlos")
                .lastName("Ramírez")
                .age(40)
                .gender("M")
                .birthDate(LocalDate.of(1984, 5, 10))
                .position("Manager")
                .build();

        when(repository.findAll()).thenReturn(List.of(emp1, emp2));

        // Act
        List<EmployeeResponse> responses = service.execute();

        // Assert
        assertThat(responses).hasSize(2);

        assertThat(responses.get(0).getId()).isEqualTo(emp1.getId());
        assertThat(responses.get(0).getFirstName()).contains("Ana");
        assertThat(responses.get(0).getPosition()).isEqualTo("Analyst");

        assertThat(responses.get(1).getId()).isEqualTo(emp2.getId());
        assertThat(responses.get(1).getFirstName()).contains("Carlos");
        assertThat(responses.get(1).getPosition()).isEqualTo("Manager");

        verify(repository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoEmployeesFound() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of());

        // Act
        List<EmployeeResponse> responses = service.execute();

        // Assert
        assertThat(responses).isEmpty();
        verify(repository).findAll();
    }
}
