package com.siscon.employee_service.delivery.api.controller;

import com.siscon.employee_service.application.service.*;
import com.siscon.employee_service.delivery.api.dto.DeleteEmployeeResponse;
import com.siscon.employee_service.delivery.api.dto.EmployeeRequest;
import com.siscon.employee_service.delivery.api.dto.EmployeeResponse;
import com.siscon.employee_service.delivery.api.dto.EmployeeUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public List<EmployeeResponse> getAll() {
        return getAllEmployeesService.execute();
    }

    @DeleteMapping("/employees/{id}")
    public DeleteEmployeeResponse delete(@PathVariable Long id) {
        return deleteEmployeeResponse.execute(id);
    }

    @PutMapping("/employees/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest request) {
        return updateEmployeeService.execute(id, request);
    }

    @PostMapping("/employees")
    public List<EmployeeResponse> create(@RequestBody @Valid List<@Valid EmployeeRequest> request) {
        logger.info("Creating employee");
        return saveEmployeesService.execute(request);
    }

    @PostMapping("/employee")
    public EmployeeResponse create(@RequestBody @Valid EmployeeRequest request) {
        logger.info("Creating employee");
        return saveEmployeeService.execute(request);
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "Buscar empleado por ID", description = "Retorna un empleado dado su identificador")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getEmployeeByIdService.execute(id));
    }

}
