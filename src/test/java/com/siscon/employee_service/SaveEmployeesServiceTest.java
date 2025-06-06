package com.siscon.employee_service;

import com.siscon.employee_service.application.service.SaveEmployeesService;
import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.adapter.EmployeeRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SaveEmployeesServiceTest {

    private EmployeeRepositoryAdapter repository;
    private SaveEmployeesService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepositoryAdapter.class);
        service = new SaveEmployeesService(repository);
    }

    @Test
    void shouldSaveMultipleEmployeesAndReturnResponses() {
        // Arrange
        EmployeeRequest req1 = new EmployeeRequest();
        req1.setFirstName("Ana");
        req1.setMiddleName("Lucía");
        req1.setLastName("García");
        req1.setSecondLastName("Pérez");
        req1.setAge(28);
        req1.setGender("F");
        req1.setBirthDate(LocalDate.of(1997, 5, 10));
        req1.setPosition("Analyst");

        EmployeeRequest req2 = new EmployeeRequest();
        req2.setFirstName("Carlos");
        req2.setMiddleName(null);
        req2.setLastName("Sánchez");
        req2.setSecondLastName("López");
        req2.setAge(35);
        req2.setGender("M");
        req2.setBirthDate(LocalDate.of(1989, 1, 20));
        req2.setPosition("Manager");

        Employee emp1 = Employee.builder()
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

        Employee emp2 = Employee.builder()
                .id(2L)
                .firstName("Carlos")
                .middleName(null)
                .lastName("Sánchez")
                .secondLastName("López")
                .age(35)
                .gender("M")
                .birthDate(LocalDate.of(1989, 1, 20))
                .position("Manager")
                .build();

        when(repository.saveAll(anyList())).thenReturn(List.of(emp1, emp2));

        // Act
        List<EmployeeResponse> responses = service.execute(List.of(req1, req2));

        // Assert
        assertThat(responses).hasSize(2);

        EmployeeResponse res1 = responses.get(0);
        assertThat(res1.getId()).isEqualTo(1L);
        assertThat(res1.getFirstName()).isEqualTo("Ana");

        EmployeeResponse res2 = responses.get(1);
        assertThat(res2.getId()).isEqualTo(2L);
        assertThat(res2.getFirstName()).isEqualTo("Carlos Sánchez López");

        verify(repository).saveAll(anyList());
    }
}
