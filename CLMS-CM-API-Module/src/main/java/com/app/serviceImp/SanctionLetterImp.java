package com.app.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.repository.SanctionLetterRepository;
import com.app.service.SanctionLetterService;

@Service
public class SanctionLetterImp implements SanctionLetterService {

	@Autowired SanctionLetterRepository sanctionRepository;
	
	@Autowired JavaMailSender jms;
	
	@Value("${spring.mail.username}")
	private String from;

	@Override
	public Double calculateEmiAmmount() {
		Integer rate =18;
		Long ansan= 1000000l;
		Integer month = 48;
		
		double loanAmmount = ansan * (rate/100);
		
		double addAmmount = ansan + loanAmmount;
		
		
		double	ammount =addAmmount/month;
		
		
	   
	   return ammount;
		// double totalAmmount = ammount;
		
		
		
		
		
		
	}
	
}
