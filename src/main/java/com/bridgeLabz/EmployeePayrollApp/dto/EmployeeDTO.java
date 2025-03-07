package com.bridgeLabz.EmployeePayrollApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor  // Generates a constructor with all arguments
@NoArgsConstructor   // Generates a no-argument constructor
public class EmployeeDTO {
    private String name;
    private double salary;
}
