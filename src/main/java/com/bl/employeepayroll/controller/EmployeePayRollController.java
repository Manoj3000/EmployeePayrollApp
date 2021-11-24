package com.bl.employeepayroll.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.bl.employeepayroll.exception.ErrorDetails;
import com.bl.employeepayroll.exception.RegisterException;
import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.service.IEmployeePayrollService;
import com.bl.employeepayroll.util.TokenUtil;

@RestController
@CrossOrigin
public class EmployeePayRollController {

	@Autowired
	private IEmployeePayrollService employeePayrollService;
	
    @Autowired
    TokenUtil tokenutil;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@PostMapping("/employee")
	public ErrorDetails addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		Employee employee = employeePayrollService.addEmployee(employeeDTO);
		
		return new ErrorDetails("user added succesfully", (long) 200, tokenutil.createToken(employee.getId()));
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeePayrollService.getAllEmployees();
	}

	@GetMapping("/employee/")
	public Employee getEmployee(@RequestHeader String token) throws RegisterException {
		return employeePayrollService.getEmployee(token);
	}

	@PutMapping("/employee")
	public ErrorDetails updateEmployee(@RequestHeader String token, @Valid @RequestBody EmployeeDTO employeeDTO) throws RegisterException {
		Employee employee = employeePayrollService.updateEmployee(token, employeeDTO);
		return new ErrorDetails("data updated succefully",(long) 200,employee);
	}

	@DeleteMapping("/employee")
	public ResponseEntity<HttpStatus> deleteEmploye(@RequestHeader String token) {
		try {
			employeePayrollService.deleteEmployee(token);
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
