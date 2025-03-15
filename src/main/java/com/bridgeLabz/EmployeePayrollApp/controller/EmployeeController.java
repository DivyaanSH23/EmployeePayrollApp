package com.bridgeLabz.EmployeePayrollApp.controller;

import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import com.bridgeLabz.EmployeePayrollApp.service.EmployeeService;
import jakarta.validation.Valid;  // <-- Added this import
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;  // <-- Added this import
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@Validated   // <-- Added for class-level validation support
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // GET all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeService.getAllEmployees();
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeService.getEmployeeById(id)
                .map(employee -> ResponseEntity.ok(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - Create new employee
    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {  // <-- Added @Valid
        log.info("Creating new employee: {}", employee);
        return employeeService.createEmployee(employee);
    }

    // PUT - Update existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee updatedEmployee  // <-- Added @Valid
    ) {
        log.info("Updating employee with ID: {}", id);
        return employeeService.updateEmployee(id, updatedEmployee)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Remove employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        if (employeeService.deleteEmployee(id)) {
            log.info("Employee with ID {} deleted successfully", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Employee with ID {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}
