package com.bl.employeepayroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.service.IEmployeePayrollService;

@RestController
public class EmployeePayRollController {

	@Autowired
	private IEmployeePayrollService employeePayrollService;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		Employee employee = employeePayrollService.addEmployee(employeeDTO);
		return employee;
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeePayrollService.getAllEmployees();
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable long id) {
		return employeePayrollService.getEmployee(id);
	}

	@PutMapping("/employee")
	public Employee updateEmployee(@RequestHeader long id, @RequestBody Employee employee) {
		return employeePayrollService.updateEmployee(id, employee);
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<HttpStatus> deleteEmploye(@PathVariable long id) {
		try {
			employeePayrollService.deleteEmployee(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
