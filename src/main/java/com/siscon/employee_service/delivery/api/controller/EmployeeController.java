package com.siscon.employee_service.delivery.api.controller;

import com.siscon.employee_service.application.service.*;
import com.siscon.employee_service.delivery.api.dto.DeleteEmployeeResponse;
import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.delivery.api.dto.EmployeeUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Validated
@RequestMapping("/api")
@Tag(name = "Employees", description = "Operaciones relacionadas con empleados")
public class EmployeeController {

    private final GetAllEmployeesService getAllEmployeesService;
    private final DeleteEmployeeService deleteEmployeeResponse;
    private final SaveEmployeeService saveEmployeeService;
    private final SaveEmployeesService saveEmployeesService;
    private final GetEmployeeByIdService getEmployeeByIdService;
    private final UpdateEmployeeService updateEmployeeService;

    @Autowired
    private HttpServletRequest servletRequest;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(
                              GetAllEmployeesService getAllEmployeesService,
                              DeleteEmployeeService deleteEmployeeResponse,
                              SaveEmployeeService saveEmployeeService,
                              SaveEmployeesService saveEmployeesService,
                              GetEmployeeByIdService getEmployeeByIdService,
                              UpdateEmployeeService updateEmployeeService
                              ) {
        this.getAllEmployeesService = getAllEmployeesService;
        this.deleteEmployeeResponse = deleteEmployeeResponse;
        this.saveEmployeeService = saveEmployeeService;
        this.saveEmployeesService = saveEmployeesService;
        this.getEmployeeByIdService = getEmployeeByIdService;
        this.updateEmployeeService = updateEmployeeService;
    }

    @GetMapping("/employees")
    @Operation(summary = "Lista a todos los empleados almacenados", description = "Lista a todos los empleados almacenados")
    public List<EmployeeResponse> getAll() {
        long start = System.currentTimeMillis();
        List<EmployeeResponse> response;
        logger.info("Retrive All Employees");

        response = getAllEmployeesService.execute();

        long time = System.currentTimeMillis() - start;
        logger.info("Employees retrieved successfully: employees={}, duration={}ms", response.size(), time);
        return response;
    }

    @DeleteMapping("/employee/{id}")
    @Operation(summary = "Elimina un empleado por ID", description = "elimina empleado")
    public DeleteEmployeeResponse delete(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        DeleteEmployeeResponse response;
        logger.info("Request id to delete:  {}", id);

        response = deleteEmployeeResponse.execute(id);

        long time = System.currentTimeMillis() - start;
        logger.info("Employee deleted successfully: id={}, duration={}ms", id, time);
        return response;
    }

    @PutMapping("/employee/{id}")
    @Operation(summary = "Actualiza un empleado por ID", description = "Actualiza un solo empleado")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest request) {
        long start = System.currentTimeMillis();
        EmployeeResponse response;
        logger.info("Request body:  {}", request);

        response = updateEmployeeService.execute(id, request);

        long time = System.currentTimeMillis() - start;
        logger.info("Employee updated successfully: id={}, duration={}ms", response.getId(), time);
        return response;
    }

    @PostMapping("/employees")
    @Operation(summary = "Crear una lista de empleados", description = "Almacena los empleados recibidos")
    public List<EmployeeResponse> create(@RequestBody @Valid List<@Valid EmployeeRequest> request) {
        long start = System.currentTimeMillis();
        List<EmployeeResponse> response;
        logger.info("Request body:  {}", request);

        response = saveEmployeesService.execute(request);

        long time = System.currentTimeMillis() - start;
        logger.info("Successfully created {} employees in {} ms",
                request.size(), time);
        return response;
    }

    @PostMapping("/employee")
    @Operation(summary = "Crear un solo empleado", description = "crea un solo empleado")
    public EmployeeResponse create(@RequestBody @Valid EmployeeRequest request) {
        long start = System.currentTimeMillis();
        EmployeeResponse response;
        logger.info("Request body: {}", request);

        response = saveEmployeeService.execute(request);
        long time = System.currentTimeMillis() - start;

        logger.info("Employee created successfully: id={}, duration={}ms", response.getId(), time);
        return response;
    }

    @GetMapping("/employee/{id}")
    @Operation(summary = "Buscar empleado por ID", description = "Retorna un empleado dado su identificador")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        ResponseEntity<EmployeeResponse> response;
        logger.info("Employee id to get: {}", id);

        response = ResponseEntity.ok(getEmployeeByIdService.execute(id));
        long time = System.currentTimeMillis() - start;
        logger.info("Employee retrieved successfully: id={}, duration={}ms", id, time);

        return response;
    }


}
