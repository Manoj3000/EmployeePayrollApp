package com.bl.employeepayroll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeePayRollController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
}
