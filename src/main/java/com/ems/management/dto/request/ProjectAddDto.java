package com.ems.management.dto.request;

import java.util.Objects;
import java.util.Set;

public class ProjectAddDto {

	private String projectName;
	private String projectDesc;
	private Long projectManagerId;
	private Set<Long> employeeId;
	public ProjectAddDto() {
		super();
	}
	public ProjectAddDto(String projectName, String projectDesc, Long projectManagerId, Set<Long> employeeId) {
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.projectManagerId = projectManagerId;
		this.employeeId = employeeId;
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
	public Set<Long> getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Set<Long> employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(employeeId, projectDesc, projectManagerId, projectName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectAddDto other = (ProjectAddDto) obj;
		return Objects.equals(employeeId, other.employeeId) && Objects.equals(projectDesc, other.projectDesc)
				&& Objects.equals(projectManagerId, other.projectManagerId)
				&& Objects.equals(projectName, other.projectName);
	}
	@Override
	public String toString() {
		return "ProjectAddDto [projectName=" + projectName + ", projectDesc=" + projectDesc + ", projectManagerId="
				+ projectManagerId + ", employeeId=" + employeeId + "]";
	}
	
	
	
}
