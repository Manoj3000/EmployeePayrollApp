package com.bl.employeepayroll.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private LocalDateTime created_at;
	private LocalDateTime updated_at;

	public Employee(EmployeeDTO employeeDTO) {	
		this.id = employeeDTO.getId();
		this.name = employeeDTO.getName();
		this.salary = employeeDTO.getSalary();
		this.created_at = LocalDateTime.now();
	}

}
