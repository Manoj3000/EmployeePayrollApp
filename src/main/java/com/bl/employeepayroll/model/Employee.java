package com.bl.employeepayroll.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bl.employeepayroll.dto.EmployeeDTO;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String name;
	private double salary;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public Employee(long id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.created_at = LocalDateTime.now();
	}

	public Employee(EmployeeDTO employeeDTO) {	
		this.id = employeeDTO.getId();
		this.name = employeeDTO.getName();
		this.salary = employeeDTO.getSalary();
		this.created_at = LocalDateTime.now();
	}

	public Employee() {	}

	public Employee(long id, String name, double salary, LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}

}
