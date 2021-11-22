package com.bl.employeepayroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bl.employeepayroll.model.Employee;

public interface IEmployeePayrollRepo extends JpaRepository<Employee, Long>{

}
