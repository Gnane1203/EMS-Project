package com.ems.management.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class TimesheetUpdateDto {
	private Long empId;
	private Long projectId;
	private LocalDate date;
	private Float hoursWorked;
	private String status;
	private Long approverId;
	private LocalDateTime approverDate;
	private String mgrComments;
	private String workingStatus;
	private String empComments;
	public TimesheetUpdateDto() {
		super();
		
	}
	public TimesheetUpdateDto(Long empId, Long projectId, LocalDate date, Float hoursWorked, String status,
			Long approverId, LocalDateTime approverDate, String mgrComments, String workingStatus, String empComments) {
		this.empId = empId;
		this.projectId = projectId;
		this.date = date;
		this.hoursWorked = hoursWorked;
		this.status = status;
		this.approverId = approverId;
		this.approverDate = approverDate;
		this.mgrComments = mgrComments;
		this.workingStatus = workingStatus;
		this.empComments = empComments;
	}
	@Override
	public String toString() {
		return "TimesheetUpdateDto [empId=" + empId + ", projectId=" + projectId + ", date=" + date + ", hoursWorked="
				+ hoursWorked + ", status=" + status + ", approverId=" + approverId + ", approverDate=" + approverDate
				+ ", mgrComments=" + mgrComments + ", workingStatus=" + workingStatus + ", empComments=" + empComments
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(approverDate, approverId, date, empComments, empId, hoursWorked, mgrComments, projectId,
				status, workingStatus);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimesheetUpdateDto other = (TimesheetUpdateDto) obj;
		return Objects.equals(approverDate, other.approverDate) && Objects.equals(approverId, other.approverId)
				&& Objects.equals(date, other.date) && Objects.equals(empComments, other.empComments)
				&& Objects.equals(empId, other.empId) && Objects.equals(hoursWorked, other.hoursWorked)
				&& Objects.equals(mgrComments, other.mgrComments) && Objects.equals(projectId, other.projectId)
				&& Objects.equals(status, other.status) && Objects.equals(workingStatus, other.workingStatus);
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
	public Long getApproverId() {
		return approverId;
	}
	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}
	public LocalDateTime getApproverDate() {
		return approverDate;
	}
	public void setApproverDate(LocalDateTime approverDate) {
		this.approverDate = approverDate;
	}
	public String getMgrComments() {
		return mgrComments;
	}
	public void setMgrComments(String mgrComments) {
		this.mgrComments = mgrComments;
	}
	public String getWorkingStatus() {
		return workingStatus;
	}
	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}
	public String getEmpComments() {
		return empComments;
	}
	public void setEmpComments(String empComments) {
		this.empComments = empComments;
	}
	
	

}
