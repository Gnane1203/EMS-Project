package com.ems.management.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="timesheet")
public class TimeSheetRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long timesheetId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_id", nullable=false)
    @JsonIgnore
    private Employee employee;
    
    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name="project_id", nullable=false)
    private Project project;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private Float hoursWorked;
    
   
    @Column(nullable = false)
    private String status = "PENDING";// SUBMITTED / APPROVED / REJECTED

    @ManyToOne
    @JoinColumn(name="approver_id")
    private Employee approver;

    private LocalDateTime approverDate;
    
//    @Column
//    private float overtimeHours = 0;
    
    @Column(name = "working_status")
    private String workingStatus; // e.g. WORKING / LEAVE / HOLIDAY
    
    private String mgrComments;
    
    private String empComments;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    
    
    //getters and setters
      
 	
	public Long getTimesheetId() {
		return timesheetId;
	}


//	public float getOvertimeHours() {
//		return overtimeHours;
//	}
//
//
//	public void setOvertimeHours(float overtimeHours) {
//		this.overtimeHours = overtimeHours;
//	}


	public void setTimesheetId(Long timesheetId) {
		this.timesheetId = timesheetId;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public Float getHoursWorked() {
		return hoursWorked;
	}


	public void setHoursWorked(Float hoursWorked) {
		this.hoursWorked = hoursWorked;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Employee getApprover() {
		return approver;
	}


	public void setApprover(Employee approver) {
		this.approver = approver;
	}


	public LocalDateTime getApproverDate() {
		return approverDate;
	}


	public void setApproverDate(LocalDateTime approverDate) {
		this.approverDate = approverDate;
	}


	public String getWorkingStatus() {
		return workingStatus;
	}


	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}


	public String getMgrComments() {
		return mgrComments;
	}


	public void setMgrComments(String mgrComments) {
		this.mgrComments = mgrComments;
	}


	public String getEmpComments() {
		return empComments;
	}


	public void setEmpComments(String empComments) {
		this.empComments = empComments;
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

	//arg constructor
	public TimeSheetRequest(Long timesheetId, Employee employee, Project project, LocalDate date, Float hoursWorked,
			String status,Employee approver, LocalDateTime approverDate, String workingStatus, String mgrComments,
			String empComments) {
		super();
		this.timesheetId = timesheetId;
		this.employee = employee;
		this.project = project;
		this.date = date;
		this.hoursWorked = hoursWorked;
		this.status = status;
		this.approver = approver;
		this.approverDate = approverDate;
		
		this.workingStatus = workingStatus;
		this.mgrComments = mgrComments;
		this.empComments = empComments;
	}	
	
	
	
	//no arg constructor
	 
	public TimeSheetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	@PrePersist
	public void createdAtPersist() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void updatedAtUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
    
}
