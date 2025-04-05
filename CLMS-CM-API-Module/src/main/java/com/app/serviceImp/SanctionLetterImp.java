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
public class SanctionLetterImp implements SanctionLetterService {

	@Autowired SanctionLetterRepository sanctionRepository;
	
	@Autowired JavaMailSender jms;
	
	@Value("${spring.mail.username}")
	private String from;


	@Override
	public void saveSanction(SanctionLetter sl, LoanApplication details) {
		// TODO Auto-generated method stub
		
		SanctionLetter sanctionl = new SanctionLetter();
		
		Integer cibilScore = details.getCibil().getCibilScore();
		Double loanAmount = details.getLoanAmount();
		
		sanctionl.setApplicantName(details.getCustomer().getCustomerName());
		sanctionl.setContactDetails(details.getCustomer().getCustomerContactNumber());
		sanctionl.setInterestType(sl.getInterestType());//user
		
		if(cibilScore>=700) {
			
			sanctionl.setLoanAmtountSanctioned(loanAmount);
			
		}else if(cibilScore>=675 && cibilScore<700) {
			
			sanctionl.setLoanAmtountSanctioned(loanAmount- 100000);
			
		}else if(cibilScore>=650 && cibilScore<675) {
			
			sanctionl.setLoanAmtountSanctioned(loanAmount-200000);
			
		}else if(cibilScore>=625 && cibilScore<650) {
			
			sanctionl.setLoanAmtountSanctioned(loanAmount-300000);
			
		}else {
			
			sanctionl.setLoanAmtountSanctioned(loanAmount-400000);
		}
		
		if(cibilScore>=700)
		{
			sanctionl.setRateOfInterest(12);
		}
		else if(cibilScore>=675 && cibilScore<700)
		{
			sanctionl.setRateOfInterest(15);
		}
		else if(cibilScore>=650 && cibilScore<675)
		{
			sanctionl.setRateOfInterest(18);
		}
		else if(cibilScore>=625 && cibilScore<650)
		{
			sanctionl.setRateOfInterest(20);
		}
		else {
			sanctionl.setRateOfInterest(25);
		}
		
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
	
}
