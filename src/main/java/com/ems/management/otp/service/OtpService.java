package com.ems.management.otp.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ems.management.dto.request.LoginOtpDTO;
import com.ems.management.models.Employee;
import com.ems.management.otp.model.OtpEntry;
import com.ems.management.otp.repo.OtpRepository;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.util.EmailService;
import com.ems.management.util.GenerateOtp;

public class OtpService {
	private final OtpRepository otpRepository;

	@Autowired
	private GenerateOtp generateOtp;
	@Autowired
    private EmployeeRepository employeeRepo;
	@Autowired
    private EmailService emailService;

	public OtpService(OtpRepository otpRepository) {
		this.otpRepository = otpRepository;
	}

	public void saveOtp(String email, String otp) {
		otpRepository.save(new OtpEntry(null, email, otp, LocalDateTime.now().plusMinutes(5)));
	}

	/**
	 * send OTP functionality
	 * @throws Exception 
	 */

	public String sendOtp(String email) throws Exception {
		try {
			Optional<Employee> emp=employeeRepo.findByEmail(email);
			if(emp.isEmpty()) {
				throw new Exception("User not found");
			}
			String otp= generateOtp.generateAnOtp();
			// mail sent and save otp
			saveOtp(email, otp);
			emailService.sendMail(email, "Email verification", "Your otp "+otp);
			return "sent OTP successfully";
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
	}
	public boolean validOtp(LoginOtpDTO details) throws Exception {
		Optional<OtpEntry> otpEntry=otpRepository.findByEmail(details.Username());
		if(otpEntry.isEmpty()) throw new Exception("Email not found");
		return details.otp().equals(otpEntry.get().getOtp());
	}

}
