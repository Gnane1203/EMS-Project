package com.ems.management.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long projectId;
    
    private String projectName;
    
    private String projectDesc;

    @ManyToOne
    @JoinColumn(name="project_manager_id")
    private Employee projectManager;

    @ManyToMany(mappedBy="projects")
    private Set<Employee> employees = new HashSet<>();
    
    // createdAt/updatedAt
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    //getters and setters

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

	public Employee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
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

	public Project(Long projectId, String projectName, String projectDesc, Employee projectManager,
			Set<Employee> employees, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.projectManager = projectManager;
		this.employees = employees;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
