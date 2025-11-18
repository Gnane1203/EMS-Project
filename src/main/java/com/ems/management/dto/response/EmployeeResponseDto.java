package com.ems.management.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EmployeeResponseDto {

	private Long empId;

	private String empName;
	private String email;
	private String gender;
	private String empType;

	private LocalDate joiningDate;
	private LocalDate resignationDate;

	private String roleName;
	private Long roleId;

	private Long primaryManagerId;
	private String primaryManagerName;

	private List<Long> projectIds;

	private boolean active;
	private boolean isAdmin;

	private String createdAt;
	private String updatedAt;

	public EmployeeResponseDto() {
		super();

	}

	public EmployeeResponseDto(Long empId, String empName, String email, String gender, String empType,
			LocalDate joiningDate, LocalDate resignationDate, String roleName, Long roleId, Long primaryManagerId,
			String primaryManagerName, List<Long> projectIds, boolean active, boolean isAdmin, String createdAt,
			String updatedAt) {
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.gender = gender;
		this.empType = empType;
		this.joiningDate = joiningDate;
		this.resignationDate = resignationDate;
		this.roleName = roleName;
		this.roleId = roleId;
		this.primaryManagerId = primaryManagerId;
		this.primaryManagerName = primaryManagerName;
		this.projectIds = projectIds;
		this.active = active;
		this.isAdmin = isAdmin;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPrimaryManagerId() {
		return primaryManagerId;
	}

	public void setPrimaryManagerId(Long primaryManagerId) {
		this.primaryManagerId = primaryManagerId;
	}

	public String getPrimaryManagerName() {
		return primaryManagerName;
	}

	public void setPrimaryManagerName(String primaryManagerName) {
		this.primaryManagerName = primaryManagerName;
	}

	public List<Long> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, createdAt, email, empId, empName, empType, gender, isAdmin, joiningDate,
				primaryManagerId, primaryManagerName, projectIds, resignationDate, roleId, roleName, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeResponseDto other = (EmployeeResponseDto) obj;
		return active == other.active && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && Objects.equals(empId, other.empId)
				&& Objects.equals(empName, other.empName) && Objects.equals(empType, other.empType)
				&& Objects.equals(gender, other.gender) && isAdmin == other.isAdmin
				&& Objects.equals(joiningDate, other.joiningDate)
				&& Objects.equals(primaryManagerId, other.primaryManagerId)
				&& Objects.equals(primaryManagerName, other.primaryManagerName)
				&& Objects.equals(projectIds, other.projectIds)
				&& Objects.equals(resignationDate, other.resignationDate) && Objects.equals(roleId, other.roleId)
				&& Objects.equals(roleName, other.roleName) && Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "EmployeeResponseDto [empId=" + empId + ", empName=" + empName + ", email=" + email + ", gender="
				+ gender + ", empType=" + empType + ", joiningDate=" + joiningDate + ", resignationDate="
				+ resignationDate + ", roleName=" + roleName + ", roleId=" + roleId + ", primaryManagerId="
				+ primaryManagerId + ", primaryManagerName=" + primaryManagerName + ", projectIds=" + projectIds
				+ ", active=" + active + ", isAdmin=" + isAdmin + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
