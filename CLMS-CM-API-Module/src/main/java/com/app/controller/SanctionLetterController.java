package com.app.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.entity.LoanApplication;
import com.app.entity.SanctionLetter;
import com.app.service.SanctionLetterService;

@RestController
@RequestMapping("/cm")
public class SanctionLetterController {
	
	@Autowired SanctionLetterService santionService;
	
	@Autowired RestTemplate rt;
	

	@GetMapping("/test")
	public String testAPIGateway() {
		return "Testing API Gateway";
	}
	
	@PostMapping("/saveSanction/{id}")
	public ResponseEntity<String> saveSanction(@PathVariable("id") Integer id,@RequestBody SanctionLetter sl)
	{
		
		System.out.println("controller class");
		
		String url = "http://localhost:9090/crm/loanApplication/getLoanApplicationDetailById/"+id;
		LoanApplication loan = rt.getForObject(url, LoanApplication.class);
		
		System.out.println(loan);
		
		return new ResponseEntity<String>(santionService.saveSanction(sl, loan), HttpStatus.OK);
	}
	
	@GetMapping("/getSanction/{id}")
	public ResponseEntity<SanctionLetter> getByID(@PathVariable("id") Integer id)
	{
		return new ResponseEntity<SanctionLetter>(santionService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getSanctionByCustomerId/{id}")
	public ResponseEntity<SanctionLetter> getByCustomerID(@PathVariable("id") Integer id)
	{
		return new ResponseEntity<SanctionLetter>(santionService.getByCustomerId(id), HttpStatus.OK);
	}
	

	@GetMapping("/getAllSanction")
	public ResponseEntity<List<SanctionLetter>> getAllSanction()
	{
		return new ResponseEntity<List<SanctionLetter>>(santionService.getAllSanctionLetters(), HttpStatus.OK);
	}
	
	
	@GetMapping("/generateSanctionLetterPDF/{id}")
	public ResponseEntity<InputStreamResource> generatePDF(@PathVariable("id") Integer id)
	{
		
		ByteArrayInputStream pdfDoc = santionService.getSanctionLetterPDF(id);
		
		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", "inline");//attachment
		headers.add("Content-Disposition", "attachment; filename=SanctionLetter.pdf");
		
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(new InputStreamResource(pdfDoc));
	}
	
	@GetMapping("/updateSanctionedLoanAmount/{id}")
	public ResponseEntity<String> updateSanctionedLoanAmount(@PathVariable("id") Integer id)
	{
		
		return new ResponseEntity<String>(santionService.updateSanctionedLoanAmount(id), HttpStatus.OK);
	}
	
	@GetMapping("/updateRateInterest/{id}")
	public ResponseEntity<String> updateRateInterest(@PathVariable("id") Integer id)
	{
		
		return new ResponseEntity<String>(santionService.updateRateInterest(id), HttpStatus.OK);
	}
	
//	@PatchMapping("/updateLoanTenureInMonth/{id}/{year}")
//	public ResponseEntity<String> updateLoanTenureInMonth(@PathVariable("id") Integer id,@PathVariable("year") Integer year)
//	{
//		
//		return new ResponseEntity<String>(santionService.updateLoanTenureInMonth(id,year), HttpStatus.OK);
//	}
	
	@GetMapping("/updateEMIAmount/{id}")
	public ResponseEntity<String> updateEMIAmount(@PathVariable("id") Integer id)
	{
			
		return new ResponseEntity<String>(santionService.updateEMIAmount(id), HttpStatus.OK);
	}
	
	@GetMapping("/acceptSanction/{id}")
	public ResponseEntity<String> acceptSanction(@PathVariable("id") Integer id)
	{
			
		return new ResponseEntity<String>(santionService.acceptSanction(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/rejectSanction/{id}")
	public ResponseEntity<String> rejectSanction(@PathVariable("id") Integer id)
	{
			
		return new ResponseEntity<String>(santionService.rejectSanction(id), HttpStatus.OK);
	}
}
