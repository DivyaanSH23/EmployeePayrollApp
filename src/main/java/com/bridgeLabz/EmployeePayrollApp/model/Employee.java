package com.bridgeLabz.EmployeePayrollApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z\\s]{2,}$",
            message = "Name must start with a capital letter and have at least 3 characters"
    )
    private String name;

    private double salary;

    @NotBlank(message = "Department cannot be blank")
    private String department;
}
