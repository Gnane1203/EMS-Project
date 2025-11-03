package com.ems.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.management.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	// find projects by manager id (if each project has a manager)
	List<Project> findByProjectManagerEmpId(Long empId);
	
    List<Project> findByProjectNameContainingIgnoreCase(String projectName);
 
}
