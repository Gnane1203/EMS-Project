package com.ems.management.util;

import java.time.LocalDateTime;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.management.dto.response.ApiResponse;
import com.ems.management.models.Employee;
import com.ems.management.models.PasswordResetToken;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.repository.PasswordResetTokenRepository;


@Service
public class PasswordResetService {

	/* when user forgot the password and clicks on forgot paswword
	 * step1 = verify the user really exists
	 * step2 = if( user exists send an OTP) true
	 *  	   else (send a text message that user doesn't exists and dont send any otp) false
	 * step3 = if true, now verify the entered otp by user is valid or not
	 * 				if valid user enters a new password,  update the old password with an new password that is being entered by the user.
	 * 			else send a message saying otp is invalid and try again
	 */
	



    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private PasswordResetTokenRepository tokenRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private GenerateOtp generateOtp;

    // step 1 = verify the user really exists and send otp to gmail
    //           and save the otp in passwordResetToken table  for record
    
    public ApiResponse<String> sendOtpToEmail(String email) {
        Optional<Employee> empOpt = employeeRepo.findByEmail(email);
        if (empOpt.isEmpty()) {
            return ApiResponse.failure("User not found", null);
        }

        // Generate 6-digit OTP
        String otp = generateOtp.generateAnOtp();

        // Set expiry time = 5 minutes
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        // Save or update OTP record
        PasswordResetToken token = tokenRepo.findByEmailAndPurpose(email, "PASSWORD_RESET")
                .orElse(new PasswordResetToken(email, otp, expiryTime, "PASSWORD_RESET"));
        token.setOtp(otp);
        token.setExpiryTime(expiryTime);
        token.setPurpose("PASSWORD_RESET");
        tokenRepo.save(token);

        // Sending  email
        emailService.sendMail(email, "HRMS portal Password Reset OTP",
                "Your OTP for password reset is: " + otp + "\n\nThis OTP will expire in 5 minutes.");

        return ApiResponse.success("OTP sent to registered email.", null);
    }

    // Step 2 now validating the OTP with the stored record
    public ApiResponse<String> verifyOtp(String email, String otp) {
        PasswordResetToken token = tokenRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No OTP found for this email"));

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            return ApiResponse.failure("OTP expired", null);
        }

        if (!token.getOtp().equals(otp)) {
            return ApiResponse.failure("Invalid OTP", null);
        }

        return ApiResponse.success("OTP verified successfully", null);
    }

    // step 3 = after validating we will update the password in the DB
    public ApiResponse<String> updatePassword(String email, String newPassword) {
        Employee emp = employeeRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        emp.setPasswordHash(passwordEncoder.encode(newPassword));
        employeeRepo.save(emp);

        // Remove used OTP
        tokenRepo.findByEmail(email).ifPresent(tokenRepo::delete);

        return ApiResponse.success("Password updated successfully.", null);
    }
}
