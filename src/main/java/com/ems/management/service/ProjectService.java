package com.ems.management.service;

import java.util.List;

import com.ems.management.models.Project;

public interface ProjectService {
	Project createProject(Project project);
    Project updateProject(Long id, Project project);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    void deleteProject(Long id);

}
