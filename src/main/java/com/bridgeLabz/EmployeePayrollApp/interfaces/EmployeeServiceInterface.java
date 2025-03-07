package com.bridgeLabz.EmployeePayrollApp.interfaces;

import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInterface {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Optional<Employee> updateEmployee(Long id, Employee updatedEmployee);
    boolean deleteEmployee(Long id);
}
