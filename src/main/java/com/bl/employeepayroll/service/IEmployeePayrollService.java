package com.bl.employeepayroll.service;

import java.util.List;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public Employee addEmployee(EmployeeDTO employeeDTO);

	public List<Employee> getAllEmployees();

	public Employee getEmployee(long id);

	public Employee updateEmployee(long id, EmployeeDTO employeeDTO);

	public void deleteEmployee(long id);

}
