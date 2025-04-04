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
	public double amountSanctioned(Integer cibil) {
		double loanAmount=1000000;
		
		if(cibil>=700) {
			loanAmount=loanAmount;
		}else if( cibil>=675 && cibil<700) {
			loanAmount = loanAmount - 100000;
		}else if(cibil>=650 && cibil<675) {
			loanAmount=loanAmount-200000;
		}else if(cibil>=625 && cibil<650) {
			loanAmount=loanAmount-300000;
		}else {
			loanAmount=loanAmount-400000;
		}
	    return loanAmount;
	}
	
	
}
