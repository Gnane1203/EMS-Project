package com.ems.management.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenerateOtp {
	public String generateAnOtp() {
		//  Generating random 6-digit OTP
		String otp = String.format("%06d", new Random().nextInt(999999));
		return otp;
	}
}
