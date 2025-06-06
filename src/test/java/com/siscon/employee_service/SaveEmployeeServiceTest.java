package com.siscon.employee_service;

import com.siscon.employee_service.application.service.SaveEmployeeService;
import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SaveEmployeeServiceTest {

    private EmployeeRepositoryAdapter repository;
    private SaveEmployeeService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepositoryAdapter.class);
        service = new SaveEmployeeService(repository);
    }

    @Test
    void shouldSaveEmployeeAndReturnResponse() {
        // Arrange
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Ana");
        request.setMiddleName("Lucía");
        request.setLastName("García");
        request.setSecondLastName("Pérez");
        request.setAge(28);
        request.setGender("F");
        request.setBirthDate(LocalDate.of(1997, 5, 10));
        request.setPosition("Analyst");

        Employee entityToSave = Employee.builder()
                .firstName("Ana")
                .middleName("Lucía")
                .lastName("García")
                .secondLastName("Pérez")
                .age(28)
                .gender("F")
                .birthDate(LocalDate.of(1997, 5, 10))
                .position("Analyst")
                .build();

        Employee savedEntity = Employee.builder()
                .id(1L)
                .firstName("Ana")
                .middleName("Lucía")
                .lastName("García")
                .secondLastName("Pérez")
                .age(28)
                .gender("F")
                .birthDate(LocalDate.of(1997, 5, 10))
                .position("Analyst")
                .build();

        when(repository.save(any(Employee.class))).thenReturn(savedEntity);

        // Act
        EmployeeResponse response = service.execute(request);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getFirstName()).isEqualTo("Ana Lucía García Pérez");
        assertThat(response.getAge()).isEqualTo(28);
        assertThat(response.getGender()).isEqualTo("F");
        assertThat(response.getBirthDate()).isEqualTo(LocalDate.of(1997, 5, 10));
        assertThat(response.getPosition()).isEqualTo("Analyst");

        verify(repository).save(any(Employee.class));
    }
}
