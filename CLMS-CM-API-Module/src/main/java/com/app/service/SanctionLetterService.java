package com.app.service;

import java.io.ByteArrayInputStream;

import com.app.entity.LoanApplication;

import com.app.entity.SanctionLetter;

public interface SanctionLetterService {

	public String saveSanction(SanctionLetter sl,LoanApplication details);

	public SanctionLetter getById(Integer id);
	
	public ByteArrayInputStream getSanctionLetterPDF(Integer id);

	public String updateSanctionedLoanAmount(Integer id);

	public String updateRateInterest(Integer id);

	public String updateLoanTenureInMonth(Integer id,Integer year);
	
	public String updateEMIAmount(Integer id);
	
}

