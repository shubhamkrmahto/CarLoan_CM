package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.SanctionLetter;

public interface SanctionLetterRepository extends JpaRepository<SanctionLetter, Integer> {
	
	public SanctionLetter findByCustomerId(Integer id);

}
