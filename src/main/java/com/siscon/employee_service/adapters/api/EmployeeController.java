package com.siscon.employee_service.adapters.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siscon.employee_service.application.service.EmployeeService;
import com.siscon.employee_service.shared.dto.DeleteEmployeeResponse;
import com.siscon.employee_service.shared.dto.EmployeeRequest;
import com.siscon.employee_service.shared.dto.EmployeeResponse;
import com.siscon.employee_service.shared.dto.EmployeeUpdateRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Validated
@RequestMapping("/api/employees")
@Tag(name = "Employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    private ObjectMapper mapper;

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
        return service.saveEmployees(request);
    }



    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }




}
