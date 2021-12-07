package com.bl.employeepayroll.service;

import java.util.List;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.exception.LoginException;
import com.bl.employeepayroll.exception.RegisterException;
import com.bl.employeepayroll.exception.UserNotFoundException;
import com.bl.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public Employee addEmployee(String loginToken, EmployeeDTO employeeDTO) throws LoginException;

	public List<Employee> getAllEmployees(String loginToken) throws LoginException;

	public Employee getEmployee(String loginToken, Long id) throws RegisterException, LoginException;

	public Employee updateEmployee(String loginToken, Long id, EmployeeDTO employeeDTO) throws RegisterException, LoginException;

	public void deleteEmployee(String loginToken, Long id) throws RegisterException, LoginException;

	public void deleteAllEmployee();

	public void deleteMultipleEmployees(String loginToken, List<Long> ids) throws LoginException;

	public String checkUser(String username, String password) throws UserNotFoundException, LoginException;

}
