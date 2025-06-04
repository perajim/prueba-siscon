package com.siscon.employee_service.adapters.api;

import com.siscon.employee_service.application.service.EmployeeService;
import com.siscon.employee_service.shared.dto.EmployeeRequest;
import com.siscon.employee_service.shared.dto.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
//@Tag(name = "Employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return service.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return service.updateEmployee(id, request);
    }

    @PostMapping
    public List<EmployeeResponse> create(@RequestBody List<@Valid EmployeeRequest> request) {
        return service.saveEmployees(request);
    }
    

}
