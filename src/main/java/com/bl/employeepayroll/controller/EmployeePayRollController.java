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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.exception.Response;
import com.bl.employeepayroll.exception.UserNotFoundException;
import com.bl.employeepayroll.exception.LoginException;
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

	@GetMapping("/sayHello")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping("/login")
	public ResponseEntity<Response> checkUser(@RequestHeader(required = false) String username, @RequestHeader String password) throws UserNotFoundException, LoginException{
		String isLogin = employeePayrollService.checkUser(username, password);
		Response response = new Response("Login successfully",(long)200, isLogin);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Response> addEmployee(@RequestHeader String loginToken, @Valid @RequestBody EmployeeDTO employeeDTO) throws LoginException {
		Employee employee = employeePayrollService.addEmployee(loginToken, employeeDTO);
		Response response = new Response("employee added succesfully", (long) 200, tokenutil.createToken(employee.getId()));
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getEmployees")
	public ResponseEntity<Response> getAllEmployees(@RequestHeader String loginToken) throws LoginException {
		List<Employee> employees = employeePayrollService.getAllEmployees(loginToken);
		Response response = new Response("get employees successfully", (long) 200, employees);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<Response> getEmployee(@RequestHeader String loginToken, @RequestHeader Long id) throws RegisterException, LoginException {
		Employee employee = employeePayrollService.getEmployee(loginToken, id);
		Response response = new Response("get employee successfully", (long) 200, employee);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/editEmployee")
	public ResponseEntity<Response> updateEmployee(@RequestHeader String loginToken, @RequestHeader Long id,
			@Valid @RequestBody EmployeeDTO employeeDTO) throws RegisterException, LoginException {
		Employee employee = employeePayrollService.updateEmployee(loginToken, id, employeeDTO);
		Response response = new Response("employee updated succefully", (long) 200, employee);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<HttpStatus> deleteEmploye(@RequestHeader String loginToken, @RequestHeader Long id) throws RegisterException {
		try {
			employeePayrollService.deleteEmployee(loginToken, id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteEmployees")
	public ResponseEntity<HttpStatus> deleteMultipleEmployees(@RequestHeader String loginToken, @RequestHeader List<Long> ids) {
		try {
			employeePayrollService.deleteMultipleEmployees(loginToken, ids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<HttpStatus> deleteAllEmploye() {
		try {
			employeePayrollService.deleteAllEmployee();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return errors;
	}
}
