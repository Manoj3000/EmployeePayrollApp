package com.bl.employeepayroll.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	private String name;
	private double salary;
	private String gender;
	private String startDate;
	private String note;
	private String profilePic;
	private List<String> department;

}
