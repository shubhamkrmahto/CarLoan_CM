package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.service.SanctionLetterService;

@RestController
@RequestMapping("/CM")
public class SanctionLetterController {
	
	@Autowired SanctionLetterService santionService;
	
	@Autowired RestTemplate rt;
	
	public Double loanAmountSanctioned() {
		String url = "http://localhost:9001/cibil/generateCibil";
		Integer cibil = rt.getForObject(url, Integer.class);
		double amount=santionService.amountSanctioned(cibil);
		return null;
	}
	
	

}
