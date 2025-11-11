package com.ems.management.util;

import com.ems.management.repository.EmployeeRepository;
import com.ems.management.repository.PasswordResetTokenRepository;
import com.ems.management.dto.response.ApiResponse;
import com.ems.management.models.Employee;
import com.ems.management.models.PasswordResetToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpLogin {

	@Autowired
	private GenerateOtp generateOtp;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private PasswordResetTokenRepository tokenRepo;

    //this method is to be used when a user wants to login using OTP this generates a otp and sends via email to user if exists.
    public ApiResponse<String> generateAndSendOtp(String email) {
        //Get email from DB
    	Optional<Employee> empOpt = employeeRepo.findByEmail(email);
    	
    	if (empOpt.isEmpty()) {
    		return ApiResponse.failure("User not found", null);
        }

        //  Generating random 6-digit OTP
    	
        String otp = generateOtp.generateAnOtp();

        //saving otp in db
        	// Set expiry time = 5 minutes
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        // Save or update OTP record
        PasswordResetToken token = tokenRepo.findByEmailAndPurpose(email, "LOGIN_OTP")
                .orElse(new PasswordResetToken(email, otp, expiryTime, "LOGIN_OTP"));
        token.setOtp(otp);
        token.setExpiryTime(expiryTime);
        token.setPurpose("LOGIN_OTP");
        tokenRepo.save(token);
        
        // Send OTP via email
        
        try {
        	 //-----we can use this single line by getting emailService util class for sending email
        	
        	 emailService.sendMail(empOpt.get().getEmail(), "Your OTP Code",
        			 			   "Dear User,\n\nYour OTP for logging into People HRMS portal is: " +
        			 			   otp + "\n\nIt is valid for 5 minutes.\n\nBest Regards,\nTeam");
        	 
        	 return ApiResponse.success("OTP sent to registered email."+ empOpt.get().getEmail() +
        			 													" | OTP: " + otp, null);
        	} catch (Exception e) {
        		
        		e.printStackTrace();
        		return ApiResponse.failure("Failed to send OTP email!", null);
        	}
    }

    //this method is to be used to verify the entered otp by user is valid or not..
    // Step 2: Verify the OTP entered by user and login into the site
    public ApiResponse<String> verifyLoginOtp(String email, String enteredOtp) {
        PasswordResetToken token = tokenRepo.findByEmailAndPurpose(email, "LOGIN_OTP")
                .orElseThrow(() -> new RuntimeException("No login OTP found for this email."));

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            return ApiResponse.failure("OTP expired!", null);
        }

        if (!token.getOtp().equals(enteredOtp)) {
            return ApiResponse.failure("Invalid OTP!", null);
        }

        // OTP verified, delete record to prevent reuse
        tokenRepo.delete(token);

        return ApiResponse.success("OTP verified successfully. Login granted.", null);
    }
}




