package com.app.serviceImp;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.app.entity.LoanApplication;
import com.app.entity.SanctionLetter;
import com.app.repository.SanctionLetterRepository;
import com.app.service.SanctionLetterService;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SanctionLetterImp implements SanctionLetterService {

	@Autowired SanctionLetterRepository sanctionRepository;
	
	@Autowired JavaMailSender jms;
	
	@Value("${spring.mail.username}")
	private String from;


	@Override
	public String saveSanction(SanctionLetter sl, LoanApplication details) {
		// TODO Auto-generated method stub
		
		SanctionLetter sanctionl = new SanctionLetter();
		
		Double loanAmount = details.getLoanAmount();
		
		sanctionl.setLoanAmount(loanAmount);
		sanctionl.setCibilScore(details.getCibil().getCibilScore());
		sanctionl.setApplicantName(details.getCustomer().getCustomerName());
		sanctionl.setContactDetails(details.getCustomer().getCustomerContactNumber());
		sanctionl.setApplicantEmail(details.getCustomer().getCustomerEmailId());
		sanctionl.setInterestType(sl.getInterestType());//user
		sanctionl.setLoanTenureInMonth(sl.getLoanTenureInMonth()*12);
		sanctionl.setModeOfPayment(sl.getModeOfPayment());//user
		sanctionl.setRemarks(sl.getRemarks());//user
		sanctionl.setSanctionDate(LocalDate.now());
		sanctionl.setStatus(sl.getStatus());//user
		sanctionl.setTermsAndCondition(sl.getTermsAndCondition());//user
		
		return "Sanction Details Saved";
	}


	@Override
	public SanctionLetter getById(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<SanctionLetter> byId = sanctionRepository.findById(id);
		
		return byId.get();
	}


	@Override
	public ByteArrayInputStream getSanctionLetterPDF(Integer id) {
		
		SanctionLetter sl = getById(id);
		
		Document docs = new Document(PageSize.A4);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		PdfWriter writer = PdfWriter.getInstance(docs, outputStream);
		
		docs.open();
		try {
//			Rectangle pageSize = docs.getPageSize();
			
			PdfContentByte content = writer.getDirectContentUnder();
//			
//			content.saveState();
//			content.setColorFill(new Color(240, 240, 240)); // Light gray
//			content.rectangle(pageSize.getLeft(), pageSize.getBottom(),
//                             pageSize.getWidth(), pageSize.getHeight());
//			content.fill();
//			content.restoreState();
		
				String watermark = "C:/Users/Asus/Pictures/Screenshots/KrushnaFinCORP.png";	
			
				Image watermarkImage = Image.getInstance(watermark);
				
				watermarkImage.setAbsolutePosition(89, 157); // position on page
	            watermarkImage.scaleToFit(419, 419); // resize if needed
	            watermarkImage.setRotationDegrees(0); // optional rotation
	            
	            
	            
	            PdfGState gs1 = new PdfGState();
	            
	            gs1.setFillOpacity(1.3f);
	            
	            content.setGState(gs1);
	            
	            content.addImage(watermarkImage);
	            
			
//		PdfImage image = new PdfImage(image, watermark, null);
		
		

		
		//String title = "Krushna FinCORP";
		
		Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, CMYKColor.BLACK);
		
		Paragraph paratitle = new Paragraph();
		paratitle.add(new Chunk("Loan Sanction Letter",titlefont).setUnderline(2f,-5f));
		paratitle.setAlignment("center");
		
		docs.add(paratitle);
		
		
		Font fontBold = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, CMYKColor.BLUE);
		
		Paragraph para2title = new Paragraph();
		para2title.add(new Chunk("Krushna FinCORP",fontBold).setUnderline(2f,-5f));
		para2title.setSpacingBefore(25);
		para2title.setAlignment("left");
		
		docs.add(para2title);
		
		
		Font fontRoman = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, CMYKColor.BLACK);
		Font fontRomanBold = FontFactory.getFont(FontFactory.TIMES_BOLD, 13, CMYKColor.BLACK);
		
		Paragraph paraAddress = new Paragraph();
		paraAddress.add(new Chunk("Sanjay Nagar, Balco\nKorba, Chhattisgarh\nPin Code :- 495684 \nPhone No. :- 9098765354 \nEmail ID :- financeserviceskrishna@gmail.com \n\nTo, \nMr. "+sl.getApplicantName()+"\nContact No. :- "+sl.getContactDetails()+"\nDate :- "+LocalDate.now(),fontRoman));
		//paraAddress.setAlignment("center");
		paraAddress.setSpacingBefore(10);
		
		docs.add(paraAddress);
		
		
//		Font title3font = FontFactory.getFont(FontFactory.HELVETICA, 15, CMYKColor.BLACK);
//		
//		Paragraph para3title = new Paragraph();
//		para3title.add(new Chunk("To, \nMr. Shubham Kumar Mahto \nContact No. :- 8888787868 \n Date :- 04/Apr/2025",title3font));
//		para3title.setSpacingBefore(20);
//		//paratitle.setAlignment("center");
//		
//		docs.add(para3title);
		
		String heading = "Dear "+sl.getApplicantName()+",\nKrishna FinCORP, Welcomes you. We are Please to inform you that your application for Car Loan of amount Rs."+sl.getLoanAmount()+"/- has been accepted. The information mentioned by you has been investigated secretly by the Company team through online addhar/pan no based given below are the details as captured in the Krishna FinCORP Recorded with us. Please go through the carefully and intimate to us immediately in case of any discrepancy. Your Application Details below:";
		
//		Font headingfont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		
		Paragraph headingpara = new Paragraph(heading,fontRoman);
		
		headingpara.setSpacingBefore(20);
		headingpara.setAlignment("justify");
		
		docs.add(headingpara);
		
//		String headingOfTable = "Applicant Details";
				
//		Font headingFontOfTable = FontFactory.getFont(FontFactory.TIMES_BOLD, 14);
		
		Paragraph headingTablepara = new Paragraph();
		headingTablepara.add(new Chunk("Applicant Details",fontBold).setUnderline(2f,-5f));
		
		headingTablepara.setSpacingBefore(20);
//		headingTablepara.setSpacingAfter(10);
		headingTablepara.setAlignment("center");
		
		docs.add(headingTablepara);
		
		PdfPTable table = new PdfPTable(2);
		table.setSpacingBefore(20);
		
		PdfPCell cell1 = new PdfPCell();
		cell1.setPadding(5);
		cell1.setPhrase(new Phrase("Sanction ID",fontRomanBold));
		table.addCell(cell1);
		
		cell1.setPhrase(new Phrase(sl.getSanctionLetterId().toString()));
		table.addCell(cell1);
		
		PdfPCell cell2 = new PdfPCell();
		cell2.setPadding(5);
		cell2.setPhrase(new Phrase("Sanction Date",fontRomanBold));
		table.addCell(cell2);
		
		cell2.setPhrase(new Phrase(sl.getSanctionDate().toString()));
		table.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell();
		cell3.setPadding(5);
		cell3.setPhrase(new Phrase("Applicant Name",fontRomanBold));
		table.addCell(cell3);
		
		cell3.setPhrase(new Phrase(sl.getApplicantName()));
		table.addCell(cell3);
		
		PdfPCell cell4 = new PdfPCell();
		cell4.setPadding(5);
		cell4.setPhrase(new Phrase("Contact Details",fontRomanBold));
		table.addCell(cell4);
		
		cell4.setPhrase(new Phrase("+91"+sl.getContactDetails().toString()));
		table.addCell(cell4);
		
		PdfPCell cell5 = new PdfPCell();
		cell5.setPadding(5);
		cell5.setPhrase(new Phrase("Loan amount Sanctioned",fontRomanBold));
		table.addCell(cell5);
		
		cell5.setPhrase(new Phrase(sl.getLoanAmtountSanctioned().toString()));
		table.addCell(cell5);
		
		PdfPCell cell6 = new PdfPCell();
		cell6.setPadding(5);
		cell6.setPhrase(new Phrase("Interest Type",fontRomanBold));
		table.addCell(cell6);
		
		cell6.setPhrase(new Phrase(sl.getInterestType()));
		table.addCell(cell6);
		
		PdfPCell cell7 = new PdfPCell();
		cell7.setPadding(5);
		cell7.setPhrase(new Phrase("Rate Of Interest",fontRomanBold));
		table.addCell(cell7);
		
		cell7.setPhrase(new Phrase(sl.getRateOfInterest().toString()+"%"));
		table.addCell(cell7);
		
		PdfPCell cell8 = new PdfPCell();
		cell8.setPadding(5);
		cell8.setPhrase(new Phrase("Loan tenure amount",fontRomanBold));
		table.addCell(cell8);
		
		cell8.setPhrase(new Phrase(sl.getLoanTenureInMonth()+" Months"));
		table.addCell(cell8);
		
		PdfPCell cell9 = new PdfPCell();
		cell9.setPadding(5);
		cell9.setPhrase(new Phrase("Monthly Amount",fontRomanBold));
		table.addCell(cell9);
		
		cell9.setPhrase(new Phrase("Rs."+sl.getMonthlyEMIAmount()+"/-"));
		table.addCell(cell9);
		
		PdfPCell cell10 = new PdfPCell();
		cell10.setPadding(5);
		cell10.setPhrase(new Phrase("Mode of Payment",fontRomanBold));
		table.addCell(cell10);
		
		cell10.setPhrase(new Phrase(sl.getModeOfPayment()));
		table.addCell(cell10);
		
		docs.add(table);
		
		String signature = "Applicant Signature\n"+sl.getApplicantName();
		
		Paragraph signaturePara = new Paragraph(signature,fontRoman);
		
		signaturePara.setSpacingBefore(30);
		
		docs.add(signaturePara);
		
		String footer = "Thank You,\nBy krushna FinCORP";
		
		Paragraph footerpara = new Paragraph(footer,fontRoman);
		
		footerpara.setSpacingBefore(20);
		
		docs.add(footerpara);
		docs.close();
		
		byte[] outputInByteArray = outputStream.toByteArray();
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(outputInByteArray);
		
		sl.setSanctionLetter(outputInByteArray);
		
		MimeMessage message = jms.createMimeMessage();
		
		MimeMessageHelper mmh = new MimeMessageHelper(message, true);
		
		ByteArrayResource resource = new ByteArrayResource(outputInByteArray);
		
		mmh.setTo(sl.getApplicantEmail());
		mmh.setSubject("Sanction Letter");
		mmh.setFrom(sl.getApplicantEmail());
		mmh.setText("Dear "+sl.getApplicantName()+",\nAttached to this mail is the Sanction Letter for the Loan Application Form you submitted at Krushna FinCORP.");
		mmh.addAttachment("SanctionLetter.pdf",resource);
		
		jms.send(message);
		
			return inputStream;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String updateSanctionedLoanAmount(Integer id) {
		
		SanctionLetter sanctionl = getById(id);
		
		Integer cibilScore = sanctionl.getCibilScore();
		
		if(cibilScore>=700) {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount());
			
			return "Loan Amount Sanctioned Update.....!";
			
		}else if(cibilScore>=675 && cibilScore<700) {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()- 100000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
			
		}else if(cibilScore>=650 && cibilScore<675) {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()-200000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
			
		}else if(cibilScore>=625 && cibilScore<650) {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()-300000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
			
		}else {
			
			sanctionl.setLoanAmtountSanctioned(sanctionl.getLoanAmount()-400000);
			sanctionRepository.save(sanctionl);
			return "Loan Amount Sanctioned Update.....!";
		}	
		
	}

	
	// update Rate Of Interest
	@Override
	public String updateRateInterest(Integer id) {

			SanctionLetter sanctionl = getById(id);
		
			Integer cibilScore = sanctionl.getCibilScore();
		
		if(cibilScore>=700)
		{
			sanctionl.setRateOfInterest(12);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else if(cibilScore>=675 && cibilScore<700)
		{
			sanctionl.setRateOfInterest(15);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else if(cibilScore>=650 && cibilScore<675)
		{
			sanctionl.setRateOfInterest(18);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else if(cibilScore>=625 && cibilScore<650)
		{
			sanctionl.setRateOfInterest(20);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		else {
			sanctionl.setRateOfInterest(25);
			sanctionRepository.save(sanctionl);
			return "Rate Of Interest Updated.....!";
		}
		
	}


	// update Loan Tuner In Month
	@Override
	public String updateLoanTenureInMonth(Integer id,Integer year) {

		SanctionLetter sanctionl = getById(id);
		if(year>2&&year<6) {
		sanctionl.setLoanTenureInMonth(year*12);
		sanctionRepository.save(sanctionl);
		return "Loan Tenure Updated...";
		}
		
		return "Loan Tenure should not be more than 6 years or less than 2 years...";
	}


	@Override
	public String updateEMIAmount(Integer id) {
		// TODO Auto-generated method stub
		
		SanctionLetter sanctionl = getById(id);
		
		if(!sanctionl.getRateOfInterest().equals(null)){
		
		Double interestAmount = sanctionl.getLoanAmtountSanctioned()*(sanctionl.getRateOfInterest()/100);

		Double totalAmountWithInterest = sanctionl.getLoanAmount()+interestAmount;

		Double	emiAmount =totalAmountWithInterest/sanctionl.getLoanTenureInMonth();
		
		sanctionl.setMonthlyEMIAmount(emiAmount);
		
		sanctionRepository.save(sanctionl);
		
		return "EMI Amount generated...";
		}
		return "Generate Rate Of Interest First";
	}
	
	
	
	
	
}
