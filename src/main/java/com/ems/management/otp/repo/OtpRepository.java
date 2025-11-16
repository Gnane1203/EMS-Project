package com.ems.management.otp.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.management.otp.model.OtpEntry;
@Repository
public interface OtpRepository extends JpaRepository<OtpEntry, Long> {
	List<OtpEntry> findByExpiryTimeBefore(LocalDateTime now);
	Optional<OtpEntry> findByEmail(String email);
	
}
