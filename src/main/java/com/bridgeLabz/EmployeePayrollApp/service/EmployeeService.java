package com.bridgeLabz.EmployeePayrollApp.service;

import com.bridgeLabz.EmployeePayrollApp.interfaces.EmployeeServiceInterface;
import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import com.bridgeLabz.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            log.error("Error fetching employees: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch employees");
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        try {
            return employeeRepository.findById(id);
        } catch (Exception e) {
            log.error("Error fetching employee with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to fetch employee with ID: " + id);
        }
    }

    @Override
    public Employee createEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            log.error("Error creating employee: {}", e.getMessage());
            throw new RuntimeException("Failed to create employee");
        }
    }

    @Override
    public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
        try {
            return employeeRepository.findById(id).map(employee -> {
                employee.setName(updatedEmployee.getName());
                employee.setSalary(updatedEmployee.getSalary());
                employee.setDepartment(updatedEmployee.getDepartment());
                return employeeRepository.save(employee);
            });
        } catch (Exception e) {
            log.error("Error updating employee with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update employee with ID: " + id);
        }
    }

    @Override
    public boolean deleteEmployee(Long id) {
        try {
            if (!employeeRepository.existsById(id)) {
                throw new RuntimeException("Employee with ID " + id + " not found");
            }
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting employee: " + e.getMessage());
        }
    }

}
