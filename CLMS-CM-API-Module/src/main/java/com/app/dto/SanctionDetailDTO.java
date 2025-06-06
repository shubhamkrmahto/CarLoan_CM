package com.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanctionDetailDTO {
	
	private Integer applicationId;
	
	private CustomerDTO customer;
	
	private Double loanAmount;
	
}
