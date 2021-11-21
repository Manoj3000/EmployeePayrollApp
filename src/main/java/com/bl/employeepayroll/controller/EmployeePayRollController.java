package com.bl.employeepayroll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.Employee;

@RestController
public class EmployeePayRollController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@PostMapping("/employee")
	public String addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = new Employee(employeeDTO);
		return employee.toString();
	}
}
