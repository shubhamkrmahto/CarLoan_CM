package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	private String customerName;
	private Long customerContactNumber;
	private String customerEmailId;
	private LoanEnquiryDTO le;

}
