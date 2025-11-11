package com.ems.management.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    
    private String purpose; // values- "PASSWORD_RESET" or "LOGIN_OTP"

    private String otp;

    private LocalDateTime expiryTime;

    // Constructors
    public PasswordResetToken() {}
    
    public PasswordResetToken(String email, String otp, LocalDateTime expiryTime, String purpose) {
        this.email = email;
        this.otp = otp;
        this.expiryTime = expiryTime;
        this.purpose = purpose;
    }

    // Getters & Setters
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) { 
    	this.email = email;
    }

    public String getOtp() {
    	return otp; 
    }
    public void setOtp(String otp) {
    	this.otp = otp; 
    }
    

    public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public LocalDateTime getExpiryTime() {
    	return expiryTime; 
    }
    public void setExpiryTime(LocalDateTime expiryTime) {
    	this.expiryTime = expiryTime; 
    }
}
