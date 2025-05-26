package com.app.entity;

import java.time.LocalDate;

import com.app.enums.SanctionLetterStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
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
	private Integer customerId;
	private LocalDate sanctionDate;
	private String applicantName;
	private Long contactDetails;
	private String applicantEmail;
	private Double loanAmtountSanctioned;
	private String interestType;
	private Integer rateOfInterest;
	private Integer loanTenureInMonth;
	private Double monthlyEMIAmount;
	private Double loanAmount;
	private String modeOfPayment;
	private String remarks;
	private String termsAndCondition;
	@Enumerated(EnumType.STRING)
	private SanctionLetterStatus status;
	private Integer cibilScore;
	
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