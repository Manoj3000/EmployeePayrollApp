package com.bl.employeepayroll.service;

import java.util.List;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.exception.RegisterException;
import com.bl.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public Employee addEmployee(EmployeeDTO employeeDTO);

	public List<Employee> getAllEmployees();

	public Employee getEmployee(String token) throws RegisterException;

	public Employee updateEmployee(String token, EmployeeDTO employeeDTO) throws RegisterException;

	public void deleteEmployee(String token) throws RegisterException;

}
