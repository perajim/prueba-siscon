package com.siscon.employee_service.delivery.api.controller;

import com.siscon.employee_service.application.service.EmployeeService;
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
@RequestMapping("/api/employees")
@Tag(name = "Employees", description = "Operaciones relacionadas con empleados")
public class EmployeeController {

    private final EmployeeService service;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping
    public List<EmployeeResponse> getAll() {
        return service.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public DeleteEmployeeResponse delete(@PathVariable Long id) {
        return service.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest request) {
        return service.updateEmployee(id, request);
    }


    @PostMapping
    public List<EmployeeResponse> create(@RequestBody @Valid List<@Valid EmployeeRequest> request) {
        logger.info("Creating employee");
        return service.saveEmployees(request);
    }



    @GetMapping("/{id}")
    @Operation(summary = "Buscar empleado por ID", description = "Retorna un empleado dado su identificador")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }




}
