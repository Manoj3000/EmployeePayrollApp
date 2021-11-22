package com.bl.employeepayroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.employeepayroll.model.Employee;

@Repository
public interface IEmployeePayrollRepo extends JpaRepository<Employee, Long>{

}
