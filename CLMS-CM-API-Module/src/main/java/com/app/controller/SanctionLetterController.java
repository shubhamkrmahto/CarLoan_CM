package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.service.SanctionLetterService;

@RestController
@RequestMapping("/CM")
public class SanctionLetterController {
	
	@Autowired SanctionLetterService santionService;
	
	@Autowired RestTemplate rt;
	
	@PatchMapping("/rateOfInterest/{cibilScore}")
	public void rateOfInterest(@PathVariable("cibilScore") Integer cibilScore)
	{
			santionService.interestRate(cibilScore);
	}
	
	

}
