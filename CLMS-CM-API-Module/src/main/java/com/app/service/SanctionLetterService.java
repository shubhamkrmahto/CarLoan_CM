package com.app.service;

import com.app.entity.LoanApplication;

import com.app.entity.SanctionLetter;

public interface SanctionLetterService {

	public void saveSanction(SanctionLetter sl,LoanApplication details);

	public SanctionLetter getById(Integer id);
	
}

