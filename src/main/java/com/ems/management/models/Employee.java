package com.ems.management.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long empId;
    
    private String empName;
    
    @Column(unique=true, nullable=false)
    private String email;
    
    @Column(nullable = false)
    private String gender; // Values: "MALE" or "FEMALE"
    
    private String passwordHash;
    
    private String empType;
    
    private LocalDate joiningDate;
    
    private LocalDate resignationDate;
    
    private Boolean active = true;
    
    private Boolean isAdmin = false;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    // primary assigned manager: stored to enforce approval rule
    @ManyToOne
    @JoinColumn(name = "primary_manager_id")
    private Employee primaryManager;

 
    @ManyToMany
    @JoinTable(name = "project_employee",
      joinColumns = @JoinColumn(name="emp_id"),
      inverseJoinColumns = @JoinColumn(name="project_id"))
    private Set<Project> projects = new HashSet<>();

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    // getters/setters
    
    
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Employee getPrimaryManager() {
		return primaryManager;
	}
	public void setPrimaryManager(Employee primaryManager) {
		this.primaryManager = primaryManager;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	public void createdAtPersist() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void updatedAtUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
	public Employee(Long empId, String empName, String email,String gender, String passwordHash, String empType,
			LocalDate joiningDate, LocalDate resignationDate, Boolean active, Boolean isAdmin, Role role,
			Employee primaryManager, Set<Project> projects) {
		super();
		this.empId = empId;
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
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
    

    
    
}
