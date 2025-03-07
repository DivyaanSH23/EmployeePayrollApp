package com.bridgeLabz.EmployeePayrollApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeePayrollAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePayrollAppApplication.class, args);
	}

}
//curl -X POST "http://localhost:8080/employees" -H "Content-Type: application/json" -d '{"name":"Divyansh Shukla", "salary":5000000, "department":"IT"}'
//curl -X PUT "http://localhost:8080/employees/1" -H "Content-Type: application/json" -d '{"name":"Shiva Shukla", "salary":6000000, "department":"HR"}'
//curl -X GET "http://localhost:8080/employees"
//PostMan -http://localhost:8080/employees