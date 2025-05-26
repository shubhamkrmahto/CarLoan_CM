package com.app.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.app.entity.LoanApplication;

import com.app.entity.SanctionLetter;

public interface SanctionLetterService {


	public String saveSanction( SanctionLetter sl,LoanApplication details);

	public SanctionLetter getById(Integer id);
	
	public SanctionLetter getByCustomerId(Integer id);
	
	public List<SanctionLetter> getAllSanctionLetters();
	
	public ByteArrayInputStream getSanctionLetterPDF(Integer id);

	public String updateSanctionedLoanAmount(Integer id);

	public String updateRateInterest(Integer id);

//	public String updateLoanTenureInMonth(Integer id,Integer year);
	
	public String updateEMIAmount(Integer id);
	
	public String acceptSanction(Integer id);
	
	public String rejectSanction(Integer id);
	
}

