package com.app.serviceImp;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.app.entity.LoanApplication;
import com.app.entity.SanctionLetter;
import com.app.repository.SanctionLetterRepository;
import com.app.service.SanctionLetterService;

@Service
public class SanctionLetterImp implements SanctionLetterService{

	@Autowired SanctionLetterRepository sanctionRepository;
	
	@Autowired JavaMailSender jms;
	
	@Value("${spring.mail.username}")
	private String from;

//<<<<<<< HEAD
//	@Override
//	public void interestRate(Integer cibilScore) {
//
//		SanctionLetter sanctionLetter = new SanctionLetter();
//		
//		
//		if(cibilScore>=700)
//		{
//			sanctionLetter.setRateOfInterest(12.00);
//		}
//		else if(cibilScore>=675 && cibilScore<700)
//		{
//			sanctionLetter.setRateOfInterest(15.00);
//		}
//		else if(cibilScore>=650 && cibilScore<675)
//		{
//			sanctionLetter.setRateOfInterest(18.00);
//		}
//		else if(cibilScore>=625 && cibilScore<650)
//		{
//			sanctionLetter.setRateOfInterest(20.00);
//		}
//		else {
//			sanctionLetter.setRateOfInterest(25.00);
//		}
//=======

	@Override
	public void saveSanction(SanctionLetter sl, LoanApplication details) {
		
		SanctionLetter sanctionl = new SanctionLetter();
		
		Integer cibilScore = details.getCibil().getCibilScore();
		Double loanAmount = details.getLoanAmount();
		
		sanctionl.setApplicantName(details.getCustomer().getCustomerName());
		sanctionl.setContactDetails(details.getCustomer().getCustomerContactNumber());
		sanctionl.setInterestType(sl.getInterestType());//user
		
		
		
		
		
		sanctionl.setLoanTenureInMonth(sl.getLoanTenureInMonth()*12);
		sanctionl.setModeOfPayment(sl.getModeOfPayment());//user
		
		Double interestAmount = sanctionl.getLoanAmtountSanctioned() * (sanctionl.getRateOfInterest()/100);

		Double totalAmountWithInterest = loanAmount + interestAmount;


		double	emiAmount =totalAmountWithInterest/sanctionl.getLoanTenureInMonth();
		
		sanctionl.setMonthlyEMIAmount(emiAmount);
		
		
		sanctionl.setRemarks(sl.getRemarks());//user
		sanctionl.setSanctionDate(LocalDate.now());
		sanctionl.setStatus(sl.getStatus());//user
		sanctionl.setTermsAndCondition(sl.getTermsAndCondition());//user
		sanctionl.setSanctionLetter(null);
		
		sanctionRepository.save(sanctionl);
		
	}

	

	@Override
	public SanctionLetter getById(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<SanctionLetter> byId = sanctionRepository.findById(id);
		
		return byId.get();
	}

	
	

	// update Sanctioned Loan Amount
	
	@Override
	public String updateSanctionedLoanAmount(Integer id, Integer cibilScore) {
		
		SanctionLetter sanctionl = getById(id);
		
		
		if(cibilScore>=700) {
			
			return "Loan Amount Sanctioned Update.....!";
			
		}else if(cibilScore>=675 && cibilScore<700) {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()- 100000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
			
		}else if(cibilScore>=650 && cibilScore<675) {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()-200000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
			
		}else if(cibilScore>=625 && cibilScore<650) {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()-300000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
			
		}else {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()-400000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
		}	
		
	}

	
	// update Rate Of Interest
	@Override
	public String updateRateInterest(Integer id, Integer cibilScore) {

			SanctionLetter sanctionl = getById(id);
		
		
		if(cibilScore>=700)
		{
			sanctionl.setRateOfInterest(12);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else if(cibilScore>=675 && cibilScore<700)
		{
			sanctionl.setRateOfInterest(15);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else if(cibilScore>=650 && cibilScore<675)
		{
			sanctionl.setRateOfInterest(18);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else if(cibilScore>=625 && cibilScore<650)
		{
			sanctionl.setRateOfInterest(20);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else {
			sanctionl.setRateOfInterest(25);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		
	}


	// update Loan Tuner In Month
	@Override
	public String updateLoanTunerInMonth(Integer id, Integer cibilScore) {

		
		return null;
	}
	
	
	
	
	
	
}
