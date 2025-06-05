package com.siscon.employee_service;


import com.siscon.employee_service.application.service.*;
import com.siscon.employee_service.delivery.api.controller.EmployeeController;
import com.siscon.employee_service.delivery.api.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTests {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private GetAllEmployeesService getAllEmployeesService;
    @Mock
    private DeleteEmployeeService deleteEmployeeService;
    @Mock
    private SaveEmployeeService saveEmployeeService;
    @Mock
    private SaveEmployeesService saveEmployeesService;
    @Mock
    private GetEmployeeByIdService getEmployeeByIdService;
    @Mock
    private UpdateEmployeeService updateEmployeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private EmployeeResponse mockEmployee() {
        return new EmployeeResponse(
                1L,
                "John Doe",
                30,
                "Male",
                LocalDate.of(1994, 1, 1),
                "Developer"
        );
    }

    private DeleteEmployeeResponse mockEmployeeDeleted() {
        return new DeleteEmployeeResponse(
                LocalDateTime.of(1986, Month.APRIL, 8, 12, 30),
                1,
                "Employee deleted successfully",
                1L
        );
    }

    @Test
    void testCreateMultiple() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Angel");
        request.setAge(25);

        List<EmployeeRequest> requestList = List.of(request);
        List<EmployeeResponse> responseList = List.of(mockEmployee());

        when(saveEmployeesService.execute(requestList)).thenReturn(responseList);

        List<EmployeeResponse> result = employeeController.create(requestList);

        assertEquals(1, result.size());
    }

    @Test
    void testCreateSingle() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Jose");
        request.setAge(25);

        EmployeeResponse response = mockEmployee();
        when(saveEmployeeService.execute(request)).thenReturn(response);

        EmployeeResponse result = employeeController.create(request);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
    }

    @Test
    void testGetAll() {
        List<EmployeeResponse> mockList = List.of(mockEmployee());
        when(getAllEmployeesService.execute()).thenReturn(mockList);

        List<EmployeeResponse> result = employeeController.getAll();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
    }



    @Test
    void testUpdate() {
        Long employeeId = 1L;
        EmployeeUpdateRequest request = new EmployeeUpdateRequest();
        request.setFirstName("Jane");
        request.setPosition("Manager");

        EmployeeResponse updated = mockEmployee();
        updated.setFullName("Jane Doe");
        updated.setPosition("Manager");

        when(updateEmployeeService.execute(employeeId, request)).thenReturn(updated);

        EmployeeResponse result = employeeController.update(employeeId, request);

        assertEquals("Jane Doe", result.getFullName());
        assertEquals("Manager", result.getPosition());
    }



    @Test
    void testDelete() {
        Long employeeId = 1L;
        DeleteEmployeeResponse response = mockEmployeeDeleted();

        response.setEmployeeId(1L);
        response.setMessage("Employee deleted successfully");


        when(deleteEmployeeService.execute(employeeId)).thenReturn(response);

        DeleteEmployeeResponse result = employeeController.delete(employeeId);
        System.out.println(result.getEmployeeId());
        assertEquals("Employee deleted successfully", result.getMessage());
    }

    @Test
    void testGetById() {
        Long id = 1L;
        EmployeeResponse response = mockEmployee();

        when(getEmployeeByIdService.execute(id)).thenReturn(response);

        ResponseEntity<EmployeeResponse> result = employeeController.getById(id);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("John Doe", result.getBody().getFullName());
    }
}
