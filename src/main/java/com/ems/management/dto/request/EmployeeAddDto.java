package com.ems.management.dto.request;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EmployeeAddDto {
	private String empName;
    private String email;
    private String gender;
    private String passwordHash;
    private String empType;

    private LocalDate joiningDate;
    private LocalDate resignationDate;
    private Boolean active = true;
    
    private Boolean isAdmin = false;

    private Long role;               
    private Long primaryManager;    

    private List<Long> projects;

	

	public EmployeeAddDto(String empName, String email, String gender, String passwordHash, String empType,
			LocalDate joiningDate, LocalDate resignationDate, Boolean active, Boolean isAdmin, Long role,
			Long primaryManager, List<Long> projects) {
		this.empName = empName;
		this.email = email;
		this.gender = gender;
		this.passwordHash = passwordHash;
		this.empType = empType;
		this.joiningDate = joiningDate;
		this.resignationDate = resignationDate;
		this.active = active;
		this.isAdmin = isAdmin;
		this.role = role;
		this.primaryManager = primaryManager;
		this.projects = projects;
	}



	public EmployeeAddDto() {
		super();
		
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getPasswordHash() {
		return passwordHash;
	}



	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}



	public String getEmpType() {
		return empType;
	}



	public void setEmpType(String empType) {
		this.empType = empType;
	}



	public LocalDate getJoiningDate() {
		return joiningDate;
	}



	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}



	public LocalDate getResignationDate() {
		return resignationDate;
	}



	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	public Boolean getIsAdmin() {
		return isAdmin;
	}



	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public Long getRole() {
		return role;
	}



	public void setRole(Long role) {
		this.role = role;
	}



	public Long getPrimaryManager() {
		return primaryManager;
	}



	public void setPrimaryManager(Long primaryManager) {
		this.primaryManager = primaryManager;
	}



	public List<Long> getProjects() {
		return projects;
	}



	public void setProjects(List<Long> projects) {
		this.projects = projects;
	}



	@Override
	public String toString() {
		return "EmployeeAddDto [empName=" + empName + ", email=" + email + ", gender=" + gender + ", passwordHash="
				+ passwordHash + ", empType=" + empType + ", joiningDate=" + joiningDate + ", resignationDate="
				+ resignationDate + ", active=" + active + ", isAdmin=" + isAdmin + ", role=" + role
				+ ", primaryManager=" + primaryManager + ", projects=" + projects + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(active, email, empName, empType, gender, isAdmin, joiningDate, passwordHash, primaryManager,
				projects, resignationDate, role);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeAddDto other = (EmployeeAddDto) obj;
		return Objects.equals(active, other.active) && Objects.equals(email, other.email)
				&& Objects.equals(empName, other.empName) && Objects.equals(empType, other.empType)
				&& Objects.equals(gender, other.gender) && Objects.equals(isAdmin, other.isAdmin)
				&& Objects.equals(joiningDate, other.joiningDate) && Objects.equals(passwordHash, other.passwordHash)
				&& Objects.equals(primaryManager, other.primaryManager) && Objects.equals(projects, other.projects)
				&& Objects.equals(resignationDate, other.resignationDate) && Objects.equals(role, other.role);
	} 
    
	
	
    
    
    

}
