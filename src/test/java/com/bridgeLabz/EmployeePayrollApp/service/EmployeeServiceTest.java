package com.bridgeLabz.EmployeePayrollApp.service;

import com.bridgeLabz.EmployeePayrollApp.model.Employee;
import com.bridgeLabz.EmployeePayrollApp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setSalary(50000);
        employee.setDepartment("Engineering");
    }


    @Test
    void getAllEmployees_Success() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));
        List<Employee> employees = employeeService.getAllEmployees();
        assertTrue(!employees.isEmpty());
    }

    @Test
    void getAllEmployees_Failure() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList());
        List<Employee> employees = employeeService.getAllEmployees();
        assertFalse(!employees.isEmpty());
    }

    @Test
    void getAllEmployees_ThrowsException() {
        when(employeeRepository.findAll()).thenThrow(new RuntimeException("Database Error"));
        assertThrows(RuntimeException.class, () -> employeeService.getAllEmployees());
    }

    // ==================== getEmployeeById() ====================
    @Test
    void getEmployeeById_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> result = employeeService.getEmployeeById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void getEmployeeById_Failure() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Employee> result = employeeService.getEmployeeById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void getEmployeeById_ThrowsException() {
        when(employeeRepository.findById(1L)).thenThrow(new RuntimeException("Database Error"));
        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(1L));
    }

    // ==================== createEmployee() ====================
    @Test
    void createEmployee_Success() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee createdEmployee = employeeService.createEmployee(employee);
        assertTrue(createdEmployee != null);
    }

    @Test
    void createEmployee_Failure() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(null);
        Employee createdEmployee = employeeService.createEmployee(employee);
        assertFalse(createdEmployee != null);
    }

    @Test
    void createEmployee_ThrowsException() {
        when(employeeRepository.save(any(Employee.class))).thenThrow(new RuntimeException("Database Error"));
        assertThrows(RuntimeException.class, () -> employeeService.createEmployee(employee));
    }

    // ==================== updateEmployee() ====================
    @Test
    void updateEmployee_Success() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(1L, employee);
        assertTrue(updatedEmployee.isPresent());
    }

    @Test
    void updateEmployee_Failure() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(1L, employee);
        assertFalse(updatedEmployee.isPresent());
    }

    @Test
    void updateEmployee_ThrowsException() {
        when(employeeRepository.findById(1L)).thenThrow(new RuntimeException("Database Error"));
        assertThrows(RuntimeException.class, () -> employeeService.updateEmployee(1L, employee));
    }

    // ==================== deleteEmployee() ====================
    @Test
    void deleteEmployee_Success() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);
        assertDoesNotThrow(() -> employeeService.deleteEmployee(1L));
    }

    @Test
    void deleteEmployee_Failure() {
        when(employeeRepository.existsById(1L)).thenReturn(false); // Mocking non-existent employee
        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployee(1L));
    }


    @Test
    void deleteEmployee_ThrowsException() {
        when(employeeRepository.existsById(1L)).thenThrow(new RuntimeException("Database Error"));
        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployee(1L));
    }
}
