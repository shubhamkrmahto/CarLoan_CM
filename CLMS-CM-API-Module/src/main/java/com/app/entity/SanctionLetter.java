package com.app.entity;

import java.time.LocalDate;

import com.app.enums.SanctionLetterStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SanctionLetter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sanctionLetterId;
	private LocalDate sanctionDate;
	private String applicantName;
	private Long contactDetails;
	private String applicantEmail;
	private Double loanAmtountSanctioned;
	private String interestType;
	private Integer cibilScore;
	private Double loanAmount;
	private Integer rateOfInterest;
	private Integer loanTenureInMonth;
	private Double monthlyEMIAmount;
	private String modeOfPayment;
	private String remarks;
	private String termsAndCondition;
	
	@Enumerated(EnumType.STRING)
	private SanctionLetterStatus status;
	
	@Lob
	@Column(length = 9000000)
	private byte[] sanctionLetter;

	
}

//{
//    "interestType":"FIXED",
//    "loanTenureInMonth":4,
//	"modeOfPayment":"ONLINE",
//	"remarks":"APPROVED BY CM",
//	"termsAndCondition":"abcdefghijklmnopqrstuvwxyz",
//    "status":"PENDING"
//}