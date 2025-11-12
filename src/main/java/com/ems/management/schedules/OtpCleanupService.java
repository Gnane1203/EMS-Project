package com.ems.management.schedules;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ems.management.otp.model.OtpEntry;
import com.ems.management.otp.repo.OtpRepository;

@Service
public class OtpCleanupService {

    private final OtpRepository otpRepository;

    public OtpCleanupService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    // Runs every 1 minute
    @Scheduled(fixedRate = 60000)
    public void removeExpiredOtps() {
        LocalDateTime now = LocalDateTime.now();
        List<OtpEntry> expired = otpRepository.findByExpiryTimeBefore(now);
        if (!expired.isEmpty()) {
            otpRepository.deleteAll(expired);
            System.out.println("🧹 Cleaned " + expired.size() + " expired OTP entries at " + now);
        }
    }
}

