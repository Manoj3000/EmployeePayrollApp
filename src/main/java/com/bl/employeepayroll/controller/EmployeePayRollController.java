package com.bl.employeepayroll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public Employee addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
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
	public Employee updateEmployee(@RequestHeader long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
		return employeePayrollService.updateEmployee(id, employeeDTO);
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
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	   Map<String, String> errors = new HashMap<>();
	 
	   ex.getBindingResult().getFieldErrors().forEach(error ->
	           errors.put(error.getField(), error.getDefaultMessage()));
	 
	   return errors;
	}
}
