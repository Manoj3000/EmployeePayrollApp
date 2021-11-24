package com.bl.employeepayroll.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee Name Invalid")
	private String name;
	
	@Min(value = 500, message = "Min Wage should be more then 500")
	private double salary;
	
	@Pattern(regexp = "male|female", message = "Gender needs to be male or female")
	private String gender;
	
	@JsonFormat(pattern = "dd-mm-yyyy")
	@NotNull(message = "Start Date should not be Empty")
//	@PastOrPresent(message = "Start Date should be past or todays date")
	@NotBlank(message = "Start Date cannot be Empty")
	private String startDate;
	
	@NotBlank(message = "Note cannot be Empty")
	private String note;
	
	@NotBlank(message = "Profile Picture cannot be Empty")
	private String profilePic;

	@NotNull(message = "Department should not be Empty")
	private List<String> department;

}
