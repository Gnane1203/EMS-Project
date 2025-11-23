package com.ems.management.service.impl;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ems.management.dto.request.ProjectAddDto;
import com.ems.management.dto.response.ProjectResDto;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.mappers.ProjectMapper;
import com.ems.management.models.Employee;
import com.ems.management.models.Project;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.repository.ProjectRepository;
import com.ems.management.service.EmployeeService;
import com.ems.management.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{


    @Autowired
    private ProjectRepository repo;
  
    private EmployeeService employeeServ;
    @Autowired
    private EmployeeRepository empRepo;
    public ProjectServiceImpl(@Lazy EmployeeService employeeServ) {
		this.employeeServ = employeeServ;
	}

	@Override
    public Project createProject(Project project) {
        return repo.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) {
    	
        Project existing = repo.findById(id).orElseThrow(() -> new ExceptionTemplate("project not found",HttpStatus.NOT_FOUND));
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
        return repo.findById(id)
        		.orElseThrow(() -> new ExceptionTemplate("Project not found with ID: " + id,HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteProject(Long id) {
        repo.deleteById(id);
        
        
        
        
    }
    // crud operations

	@Override
	public ProjectResDto createProject(ProjectAddDto dto) {
		Project project= ProjectMapper.toEntity(dto);
		Employee manager=employeeServ.getById(dto.getProjectManagerId());
		project.setProjectManager(manager);
		project.setEmployees(
		        dto.getEmployeeId()
		                .stream()
		                .map(employeeServ::getById)
		                .collect(Collectors.toCollection(LinkedHashSet::new))
		);

		Project newProject=repo.save(project);
		Set<Project> mproject=manager.getProjects();
		mproject.add(newProject);
		manager.setProjects(mproject);
		empRepo.save(manager);
		
		project.getEmployees()
        .stream()
        .map(emp -> {
            emp.getProjects().add(newProject);
            return emp;
        })
        .forEach(empRepo::save);

		
		return ProjectMapper.toResDto(newProject);
	}

	@Override
	public ProjectResDto updateProject(Long id, ProjectAddDto dto) {
		Project project =getProjectById(id);
		project.setProjectName(dto.getProjectName());
		project.setProjectDesc(dto.getProjectDesc());
		Employee manager=employeeServ.getById(dto.getProjectManagerId());
		project.setProjectManager(manager);
		project.setEmployees(
		        dto.getEmployeeId()
		                .stream()
		                .map(employeeServ::getById)
		                .collect(Collectors.toCollection(LinkedHashSet::new))
		);

		Project newProject=repo.save(project);
		Set<Project> mproject=manager.getProjects();
		mproject.add(newProject);
		manager.setProjects(mproject);
		empRepo.save(manager);
		
		project.getEmployees()
        .stream()
        .map(emp -> {
            emp.getProjects().add(newProject);
            return emp;
        })
        .forEach(empRepo::save);

		
		return ProjectMapper.toResDto(newProject);
		

	}

	@Override
	public List<ProjectResDto> getAllProjectDtos() {
		return repo.findAll()
		        .stream()
		        .map(ProjectMapper::toResDto)
		        .collect(Collectors.toList());

	}

	@Override
	public ProjectResDto getProjectResById(Long id) {
		Project p=getProjectById(id);
		
		return ProjectMapper.toResDto(p) ;
	}

	@Override
    public Page<ProjectResDto> searchProjectsByManager(Long managerId, String keyword, int page, int size, String sortBy, String sortDir) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDir.equalsIgnoreCase("desc")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending()
        );

        return repo
                .findByProjectManager_EmpIdAndProjectNameContainingIgnoreCase(
                        managerId, keyword, pageable)
                .map(ProjectMapper::toResDto);
    }
}

