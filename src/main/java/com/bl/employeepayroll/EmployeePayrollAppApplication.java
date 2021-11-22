package com.bl.employeepayroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeePayrollAppApplication {

	public static final Logger logger = LoggerFactory.getLogger(EmployeePayrollAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EmployeePayrollAppApplication.class, args);
		logger.info("Welcome To Employee Payroll AppApplication");
	}
}

