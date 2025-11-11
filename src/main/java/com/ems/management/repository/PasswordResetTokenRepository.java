package com.ems.management.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ems.management.models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByEmail(String email);
    Optional<PasswordResetToken> findByEmailAndPurpose(String email, String purpose);
}
