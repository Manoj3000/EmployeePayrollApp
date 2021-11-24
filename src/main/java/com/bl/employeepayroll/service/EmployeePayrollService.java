package com.bl.employeepayroll.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.exception.RegisterException;
import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.repository.IEmployeePayrollRepo;
import com.bl.employeepayroll.util.TokenUtil;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

	@Autowired
	private IEmployeePayrollRepo employeeRepo;

	@Autowired
	TokenUtil tokenutil;

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
	public Employee getEmployee(String token) throws RegisterException {
		Long id = tokenutil.decodeToken(token);
		Optional<Employee> employee = employeeRepo.findById(id);
		if (employee.isPresent())
			return employee.get();
		else
			throw new RegisterException("No Employee record exist for given id", 400);
	}

	@Override
	public Employee updateEmployee(String token, EmployeeDTO employeeDTO) throws RegisterException {
		Long id = tokenutil.decodeToken(token);

		Optional<Employee> employee = employeeRepo.findById(id);
		if (employee.isPresent()) {
			Employee emp = employee.get();
			emp.setName(employeeDTO.getName());
			emp.setSalary(employeeDTO.getSalary());
			emp.setGender(employeeDTO.getGender());
			emp.setStartDate(employeeDTO.getStartDate());
			emp.setNote(employeeDTO.getNote());
			emp.setProfilePic(employeeDTO.getProfilePic());
			emp.setDepartment(employeeDTO.getDepartment());
			emp.setUpdated_at(LocalDateTime.now());
			return employeeRepo.save(emp);
		} else {
			throw new RegisterException("No Employee record exist for given id", 400);
		}
	}

	@Override
	public void deleteEmployee(String token) throws RegisterException {
		Long id = tokenutil.decodeToken(token);
		Optional<Employee> employee = employeeRepo.findById(id);
		if (employee.isPresent()) {
			employeeRepo.delete(employee.get());
		} else {
			throw new RegisterException("No Employee record exist for given id", 400);
		}
	}
}
