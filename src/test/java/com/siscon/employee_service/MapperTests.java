package com.siscon.employee_service;

import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.domain.model.Employee;
import com.siscon.employee_service.infrastructure.mappers.Mapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MapperTest {

    @Test
    void toEntity_shouldMapRequestToEntityCorrectly() {
        // Arrange
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Juan");
        request.setMiddleName("Carlos");
        request.setLastName("Pérez");
        request.setSecondLastName("Gómez");
        request.setAge(30);
        request.setGender("M");
        request.setBirthDate(LocalDate.of(1993, 5, 10));
        request.setPosition("Developer");

        // Act
        Employee employee = Mapper.toEntity(request);

        // Assert
        assertThat(employee.getFirstName()).isEqualTo("Juan");
        assertThat(employee.getMiddleName()).isEqualTo("Carlos");
        assertThat(employee.getLastName()).isEqualTo("Pérez");
        assertThat(employee.getSecondLastName()).isEqualTo("Gómez");
        assertThat(employee.getAge()).isEqualTo(30);
        assertThat(employee.getGender()).isEqualTo("M");
        assertThat(employee.getBirthDate()).isEqualTo(LocalDate.of(1993, 5, 10));
        assertThat(employee.getPosition()).isEqualTo("Developer");
    }

    @Test
    void toResponse_shouldMapEntityToResponseCorrectly() {
        // Arrange
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Ana")
                .middleName(null) // Verifica manejo de null
                .lastName("López")
                .secondLastName("Martínez")
                .age(28)
                .gender("F")
                .birthDate(LocalDate.of(1996, 2, 14))
                .position("Designer")
                .build();

        // Act
        EmployeeResponse response = Mapper.toResponse(employee);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getFirstName()).isEqualTo("Ana López Martínez");
        assertThat(response.getAge()).isEqualTo(28);
        assertThat(response.getGender()).isEqualTo("F");
        assertThat(response.getBirthDate()).isEqualTo(LocalDate.of(1996, 2, 14));
        assertThat(response.getPosition()).isEqualTo("Designer");
    }
}
