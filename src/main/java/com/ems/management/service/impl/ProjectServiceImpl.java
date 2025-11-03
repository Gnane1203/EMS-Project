package com.ems.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ems.management.models.Project;
import com.ems.management.repository.ProjectRepository;
import com.ems.management.service.ProjectService;

public class ProjectServiceImpl implements ProjectService{


    @Autowired
    private ProjectRepository repo;

    @Override
    public Project createProject(Project project) {
        return repo.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) {
    	
        Project existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        existing.setProjectName(project.getProjectName());
        existing.setProjectDesc(project.getProjectDesc());
        existing.setProjectManager(project.getProjectManager());
        return repo.save(existing);
    }

    @Override
    public List<Project> getAllProjects() {
        return repo.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteProject(Long id) {
        repo.deleteById(id);
    }
}
