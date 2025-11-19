package com.ems.management.dto.response;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class ProjectResDto {
	private Long projectId;
	private String projectName;
	private String projectDesc;
	private Long projectManagerId;
	private String projectManagerName;
	private Set<Long> employeeId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public ProjectResDto() {
		super();
	}
	public ProjectResDto(Long projectId, String projectName, String projectDesc, Long projectManagerId,
			String projectManagerName, Set<Long> employeeId, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.projectManagerId = projectManagerId;
		this.projectManagerName = projectManagerName;
		this.employeeId = employeeId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public Long getProjectManagerId() {
		return projectManagerId;
	}
	public void setProjectManagerId(Long projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	public String getProjectManagerName() {
		return projectManagerName;
	}
	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}
	public Set<Long> getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Set<Long> employeeId) {
		this.employeeId = employeeId;
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
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, employeeId, projectDesc, projectId, projectManagerId, projectManagerName,
				projectName, updatedAt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectResDto other = (ProjectResDto) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(employeeId, other.employeeId)
				&& Objects.equals(projectDesc, other.projectDesc) && Objects.equals(projectId, other.projectId)
				&& Objects.equals(projectManagerId, other.projectManagerId)
				&& Objects.equals(projectManagerName, other.projectManagerName)
				&& Objects.equals(projectName, other.projectName) && Objects.equals(updatedAt, other.updatedAt);
	}
	@Override
	public String toString() {
		return "ProjectResDto [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc=" + projectDesc
				+ ", projectManagerId=" + projectManagerId + ", projectManagerName=" + projectManagerName
				+ ", employeeId=" + employeeId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
	

}
