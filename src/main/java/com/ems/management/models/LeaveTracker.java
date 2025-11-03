package com.ems.management.models;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "leave_tracker")
public class LeaveTracker {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonIgnore
    private Employee employee;

    @Column(nullable = false)
    private int casualLeaves = 0;

    @Column(nullable = false)
    private int sickLeaves = 0;

    @Column(nullable = false)
    private int maternityLeaves = 0;

    @Column(nullable = false)
    private int usedLeaves = 0;
        
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    // ✅ Constructors
    public LeaveTracker() {
    	
    }

    //all arg constructor
    public LeaveTracker(Employee employee, int casualLeaves, int sickLeaves, int maternityLeaves, int usedLeaves) {
        this.employee = employee;
        this.casualLeaves = casualLeaves;
        this.sickLeaves = sickLeaves;
        this.maternityLeaves = maternityLeaves;
        this.usedLeaves = usedLeaves;
    }

    // ✅ Getters & Setters
    
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

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getCasualLeaves() {
        return casualLeaves;
    }

    public void setCasualLeaves(int casualLeaves) {
        this.casualLeaves = casualLeaves;
    }

    public int getSickLeaves() {
        return sickLeaves;
    }

    public void setSickLeaves(int sickLeaves) {
        this.sickLeaves = sickLeaves;
    }

    public int getMaternityLeaves() {
        return maternityLeaves;
    }

    public void setMaternityLeaves(int maternityLeaves) {
        this.maternityLeaves = maternityLeaves;
    }

    public int getUsedLeaves() {
        return usedLeaves;
    }

    public void setUsedLeaves(int usedLeaves) {
        this.usedLeaves = usedLeaves;
    }

    // ✅ Utility method (optional)
    public int getTotalAvailableLeaves() {
        return (casualLeaves + sickLeaves + maternityLeaves) - usedLeaves;
    }
}
