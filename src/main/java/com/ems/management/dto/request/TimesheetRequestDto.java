package com.ems.management.dto.request;

import java.time.LocalDate;
import java.util.Objects;

public class TimesheetRequestDto {
	private Long empId;
	private Long projectId;
	private LocalDate date;
	private Float hoursWorked;
	private String workingStatus;
	private String empComments;
	public TimesheetRequestDto() {
		super();
		
	}
	public TimesheetRequestDto(Long empId, Long projectId, LocalDate date, Float hoursWorked, String workingStatus,
			String empComments) {
		this.empId = empId;
		this.projectId = projectId;
		this.date = date;
		this.hoursWorked = hoursWorked;
		this.workingStatus = workingStatus;
		this.empComments = empComments;
	}
	@Override
	public String toString() {
		return "TimesheetRequestDto [empId=" + empId + ", projectId=" + projectId + ", date=" + date + ", hoursWorked="
				+ hoursWorked + ", workingStatus=" + workingStatus + ", empComments=" + empComments + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(date, empComments, empId, hoursWorked, projectId, workingStatus);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimesheetRequestDto other = (TimesheetRequestDto) obj;
		return Objects.equals(date, other.date) && Objects.equals(empComments, other.empComments)
				&& Objects.equals(empId, other.empId) && Objects.equals(hoursWorked, other.hoursWorked)
				&& Objects.equals(projectId, other.projectId) && Objects.equals(workingStatus, other.workingStatus);
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
