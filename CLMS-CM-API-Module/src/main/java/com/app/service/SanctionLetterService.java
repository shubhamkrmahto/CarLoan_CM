package com.app.service;

import com.app.entity.LoanApplication;

import com.app.entity.SanctionLetter;

public interface SanctionLetterService {

//<<<<<<< HEAD
//	public void interestRate(Integer cibilScore);
//=======
	public void saveSanction(SanctionLetter sl,LoanApplication details);

	public SanctionLetter getById(Integer id);

	public String updateSanctionedLoanAmount(Integer id, Integer cibilScore);

	public String updateRateInterest(Integer id, Integer cibilScore);

	public String updateLoanTunerInMonth(Integer id, Integer cibilScore);
	
}

