package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {

	private Double loanAmount;
	private Cibil cibil;
	private Customer customer;
	
}
