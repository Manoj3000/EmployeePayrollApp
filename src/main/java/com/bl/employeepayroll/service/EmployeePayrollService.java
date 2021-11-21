package com.bl.employeepayroll.service;

import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.Employee;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

	@Override
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee(employeeDTO);
		return employee;
	}

}
