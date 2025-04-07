package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.app.entity.LoanApplication;
import com.app.entity.SanctionLetter;
import com.app.service.SanctionLetterService;

@RestController
@RequestMapping("/CM")
public class SanctionLetterController {
	
	@Autowired SanctionLetterService santionService;
	
	@Autowired RestTemplate rt;
//	
//<<<<<<< HEAD
//	@PatchMapping("/rateOfInterest/{cibilScore}")
//	public void rateOfInterest(@PathVariable("cibilScore") Integer cibilScore)
//	{
//			santionService.interestRate(cibilScore);
//	}
//	
//=======
	@PostMapping("/saveSanction/{id}")
	public ResponseEntity<String> saveSanction(@PathVariable("id") Integer id,@RequestBody SanctionLetter sl)
	{
		
		System.out.println("controller class");
		
		String url = "http://localhost:7002/loanApplication/getLoanApplicationDetailById/"+id;
		LoanApplication loan = rt.getForObject(url, LoanApplication.class);
		
		System.out.println(loan);
		
		santionService.saveSanction(sl, loan);
		
		return new ResponseEntity<String>("Sanction Letter sent to the registered email", HttpStatus.ACCEPTED);
	}

	
	
	@GetMapping("/getSanction/{id}")
	public ResponseEntity<SanctionLetter> getByID(@PathVariable("id") Integer id)
	{
		return new ResponseEntity<SanctionLetter>(santionService.getById(id), HttpStatus.OK);
	}
	
	
	
	// update Sanctioned Loan Amount
	
	@PatchMapping("/updateSanctionAmount/{id}/{cibilScore}")
	public ResponseEntity<String> sanctionLoanAmount(@PathVariable("id") Integer id , @PathVariable("cibilScore") Integer cibilScore)
	{
		String updateSanctionedLoanAmount = santionService.updateSanctionedLoanAmount(id,cibilScore);
		
		return new ResponseEntity<String>(updateSanctionedLoanAmount,HttpStatus.OK);
	}
	
	
	// update Rate Of Interest
	
	@PatchMapping("/updateRateOfInterest/{id}/{cibilScore}")
	public ResponseEntity<String> updateRateOfInterest(@PathVariable("id") Integer id , @PathVariable("cibilScore") Integer cibilScore)
	{
		
		String updateRestInterest = santionService.updateRateInterest(id,cibilScore);
		
		return new ResponseEntity<String>(updateRestInterest,HttpStatus.OK);
	}
	
	
	// update Loan Tuner In Month
	
		@PatchMapping("/updateRateOfInterest/{id}/{cibilScore}")
		public ResponseEntity<String> updateLoanTunerInMonth(@PathVariable("id") Integer id , @PathVariable("cibilScore") Integer cibilScore)
		{
			
			String updateLoanTuner = santionService.updateLoanTunerInMonth(id,cibilScore);
			
			return new ResponseEntity<String>(updateLoanTuner,HttpStatus.OK);
		}
}
