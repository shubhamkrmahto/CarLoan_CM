package com.app.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.entity.SanctionLetter;
import com.app.repository.SanctionLetterRepository;
import com.app.service.SanctionLetterService;

@Service
public class SanctionLetterImp implements SanctionLetterService{

	@Autowired SanctionLetterRepository sanctionRepository;
	
	@Autowired JavaMailSender jms;
	
	@Value("${spring.mail.username}")
	private String from;

	@Override
	public void interestRate(Integer cibilScore) {

		SanctionLetter sanctionLetter = new SanctionLetter();
		
		
		if(cibilScore>=700)
		{
			sanctionLetter.setRateOfInterest(12.00);
		}
		else if(cibilScore>=675 && cibilScore<700)
		{
			sanctionLetter.setRateOfInterest(15.00);
		}
		else if(cibilScore>=650 && cibilScore<675)
		{
			sanctionLetter.setRateOfInterest(18.00);
		}
		else if(cibilScore>=625 && cibilScore<650)
		{
			sanctionLetter.setRateOfInterest(20.00);
		}
		else {
			sanctionLetter.setRateOfInterest(25.00);
		}
	}
	
}
