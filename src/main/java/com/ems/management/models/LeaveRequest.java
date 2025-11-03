package com.ems.management.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "leave_request")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveRequestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonIgnore
    private Employee employee;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_id")
    @JsonIgnore
    private Employee approver;
    
    private LocalDateTime approvedDate;

    @Column(name = "leave_type", nullable = false)
    private String leaveType; // e.g. CASUAL, SICK, MATERNITY, comp-off

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "no_of_days", nullable = false)
    private int noOfDays;

    @Column(nullable = false)
    private String status = "PENDING"; // PENDING / APPROVED / REJECTED

    private String mgrComments;
    private String empComments;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    //constructor(no arg)

	public LeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	//arg constructor( )
	public LeaveRequest(Long leaveRequestId, Employee employee, Employee approver, LocalDateTime approvedDate,
			String leaveType, LocalDate startDate, LocalDate endDate, int noOfDays, String status, String mgrComments,
			String empComments) {
		super();
		this.leaveRequestId = leaveRequestId;
		this.employee = employee;
		this.approver = approver;
		this.approvedDate = approvedDate;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.noOfDays = noOfDays;
		this.status = status;
		this.mgrComments = mgrComments;
		this.empComments = empComments;
	}
	
	//getters and setters
	
	
	public Long getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(Long leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public LocalDateTime getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	@PrePersist
	public void createdAtPersist() {
		createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void updatedAtUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
	
	

	
	
    
    
 }
