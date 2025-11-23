package com.ems.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ems.management.models.Project;
import com.ems.management.dto.request.ProjectAddDto;
import com.ems.management.dto.response.ProjectResDto;

public interface ProjectService {

    // ------- ENTITY CRUD (your existing methods) -------
    Project createProject(Project project);
    Project updateProject(Long id, Project project);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    void deleteProject(Long id);

    
    // ------- NEW DTO BASED METHODS -------
    
    // CREATE from ProjectAddDto
    ProjectResDto createProject(ProjectAddDto dto);

    // UPDATE using ProjectAddDto
    ProjectResDto updateProject(Long id, ProjectAddDto dto);

    // GET ALL as ProjectResDto
    List<ProjectResDto> getAllProjectDtos();

    // GET BY ID as ProjectResDto
    ProjectResDto getProjectResById(Long id);
	Page<ProjectResDto> searchProjectsByManager(Long managerId, String keyword, int page, int size, String sortBy, String sortDir);
}
