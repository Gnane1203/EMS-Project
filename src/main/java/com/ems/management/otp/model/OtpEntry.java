package com.ems.management.otp.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "otp_codes")
public class OtpEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @Column(unique = true, nullable = false)
    private String email;
    private String otp;
    private LocalDateTime expiryTime;
	public OtpEntry(Long id, String email, String otp, LocalDateTime expiryTime) {
		super();
		this.id = id;
		this.email = email;
		this.otp = otp;
		this.expiryTime = expiryTime;
	}
	public OtpEntry() {
		super();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	@Override
	public String toString() {
		return "OtpEntry [id=" + id + ", email=" + email + ", otp=" + otp + ", expiryTime=" + expiryTime + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, expiryTime, id, otp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OtpEntry other = (OtpEntry) obj;
		return Objects.equals(email, other.email) && Objects.equals(expiryTime, other.expiryTime)
				&& Objects.equals(id, other.id) && Objects.equals(otp, other.otp);
	}
    
    
}

