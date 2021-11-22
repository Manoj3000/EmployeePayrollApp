package com.bl.employeepayroll.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.repository.IEmployeePayrollRepo;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {
	
	@Autowired
	private IEmployeePayrollRepo employeeRepo; 
	
	@Override
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee(employeeDTO);
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployee(long id) {
		return employeeRepo.findById(id).get();
	}

	@Override
	public Employee updateEmployee(long id, Employee employee) {
		Employee emp = employeeRepo.findById(id).get();
		emp.setName(employee.getName());
		emp.setSalary(employee.getSalary());
		emp.setUpdated_at(LocalDateTime.now());
		return employeeRepo.save(emp);
	}

	@Override
	public void deleteEmployee(long id) {
		Employee employee = employeeRepo.findById(id).get();
		employeeRepo.delete(employee);
	}
}
