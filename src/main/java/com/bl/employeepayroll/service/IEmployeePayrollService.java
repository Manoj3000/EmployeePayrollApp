package com.bl.employeepayroll.service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.Employee;

public interface IEmployeePayrollService {
	
	public Employee addEmployee(EmployeeDTO employeeDTO);
}
