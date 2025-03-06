package com.bridgeLabz.EmployeePayrollApp.service;

import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    public Employee createEmployee(Employee employee) {
        employee.setId((long) (employeeList.size() + 1));
        employeeList.add(employee);
        return employee;
    }

    public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
        return getEmployeeById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
            return employee;
        });
    }

    public boolean deleteEmployee(Long id) {
        return employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}
