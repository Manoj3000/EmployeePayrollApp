package com.bl.employeepayroll.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.employeepayroll.dto.EmployeeDTO;
import com.bl.employeepayroll.exception.LoginException;
import com.bl.employeepayroll.exception.RegisterException;
import com.bl.employeepayroll.exception.UserNotFoundException;
import com.bl.employeepayroll.model.Employee;
import com.bl.employeepayroll.repository.IEmployeePayrollRepo;
import com.bl.employeepayroll.util.TokenUtil;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

	private long user_id = 101;
	private String username = "ManojG";
	private String password = "Manoj@123";

	@Autowired
	private IEmployeePayrollRepo employeeRepo;

	@Autowired
	TokenUtil tokenutil;

	@Override
	public Employee addEmployee(String loginToken, EmployeeDTO employeeDTO) throws LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			Employee employee = new Employee(employeeDTO);
			return employeeRepo.save(employee);			
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public List<Employee> getAllEmployees(String loginToken) throws LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			return employeeRepo.findAll();			
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public Employee getEmployee(String loginToken, Long id) throws RegisterException, LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
//		Long id = tokenutil.decodeToken(token);
			Optional<Employee> employee = employeeRepo.findById(id);
			if (employee.isPresent())
				return employee.get();
			else
				throw new RegisterException("No Employee record exist for given id", 400);			
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public Employee updateEmployee(String loginToken, Long id, EmployeeDTO employeeDTO) throws RegisterException, LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
//		Long id = tokenutil.decodeToken(token);
			
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
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public void deleteEmployee(String loginToken, Long id) throws RegisterException, LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
//		Long id = tokenutil.decodeToken(token);
			Optional<Employee> employee = employeeRepo.findById(id);
			if (employee.isPresent()) {
				employeeRepo.delete(employee.get());
			} else {
				throw new RegisterException("No Employee record exist for given id", 400);
			}
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public void deleteAllEmployee() {
		employeeRepo.deleteAll();
	}

	@Override
	public void deleteMultipleEmployees(String loginToken, List<Long> ids) throws LoginException {
		if(tokenutil.decodeToken(loginToken) == user_id) {
			ids.forEach(id -> {
				if (employeeRepo.existsById(id)) {
					employeeRepo.deleteById(id);
				}
			});			
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public String checkUser(String username, String password) throws UserNotFoundException, LoginException {
		if (username.equals(this.username)) {
			if (password.equals(this.password)) {
				return tokenutil.createToken(this.user_id);
			} else {
				throw new LoginException("Wrong Password!");
			}
		} else {
			throw new UserNotFoundException("User not exist");
		}
	}
}
