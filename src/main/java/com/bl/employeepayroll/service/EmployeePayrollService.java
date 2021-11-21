package com.bl.employeepayroll.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.Employee;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {
	
	private List<Employee> employeeList;
	
	public EmployeePayrollService() {
		employeeList = new ArrayList<>();
	}
	
	@Override
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee(employeeDTO);
		return employeeList.add(employee) ?  employee : null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	@Override
	public Employee getEmployee(long id) {
		for (Employee employee : employeeList) {
			if(employee.getId() == id ) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public Employee updateEmployee(long id, Employee employee) {
		for (Employee emp : employeeList) {
			if(emp.getId() == id ) {
				emp.setName(employee.getName());
				emp.setSalary(employee.getSalary());
				emp.setUpdated_at(LocalDateTime.now());
				return emp;
			}
		}		
		return null;
	}

	@Override
	public void deleteEmployee(long id) {
		employeeList.removeIf(emp->emp.getId()==id);
	}
}
