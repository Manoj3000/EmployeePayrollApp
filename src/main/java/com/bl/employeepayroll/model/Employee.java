package com.bl.employeepayroll.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.bl.employeepayroll.dto.EmployeeDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String name;
	private double salary;
	private String gender;
	private String startDate;
	private String note;
	private String profilePic;
	
	@ElementCollection
	@CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "employee_id"))
	private List<String> department;
	
	private LocalDateTime created_at;
	private LocalDateTime updated_at;

	public Employee(EmployeeDTO employeeDTO) {
		this.name = employeeDTO.getName();
		this.salary = employeeDTO.getSalary();
		this.gender = employeeDTO.getGender();
		this.startDate = employeeDTO.getStartDate();
		this.note = employeeDTO.getNote();
		this.profilePic = employeeDTO.getProfilePic();
		this.department = employeeDTO.getDepartment();
		this.created_at = LocalDateTime.now();
	}

}
