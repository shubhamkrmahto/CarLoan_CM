package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.SanctionLetter;

public interface SanctionLetterRepository extends JpaRepository<SanctionLetter, Integer> {

}
