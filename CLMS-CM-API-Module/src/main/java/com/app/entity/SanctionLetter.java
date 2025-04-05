package com.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private Double loanAmtountSanctioned;
	private String interestType;
	private Integer rateOfInterest;
	private Integer loanTenureInMonth;
	private Double monthlyEMIAmount;
	private String modeOfPayment;
	private String remarks;
	private String termsAndCondition;
	private String status;
	
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