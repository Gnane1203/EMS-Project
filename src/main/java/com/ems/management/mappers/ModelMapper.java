package com.ems.management.mappers;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.ems.management.dto.request.ProjectAddDto;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.models.Employee;
import com.ems.management.models.Project;
import com.ems.management.repository.EmployeeRepository;

public class ModelMapper {
	
	
	@Autowired
	private EmployeeRepository empRepository;
	
	public Project projectModelSaveMapper(Project project, ProjectAddDto projectDto) {
		project.setProjectName(projectDto.getProjectName());
		project.setProjectDesc(projectDto.getProjectDesc());
		Employee manager=empRepository.findById(projectDto.getProjectManagerId())
				.orElseThrow(()->  new ExceptionTemplate("manager not found", HttpStatus.NOT_FOUND));
		project.setProjectManager(manager);
		Set<Employee> teamMembers=new LinkedHashSet<>();
		for(long id:projectDto.getEmployeeId()) {
			Employee emp=findEmployee(id);
			teamMembers.add(emp);
		}
		project.setEmployees(teamMembers);
		return project;
	}
	
	public Project projectModelUpdateMapper(Project project, ProjectAddDto projectDto) {
		project.setProjectName(projectDto.getProjectName());
		project.setProjectDesc(projectDto.getProjectDesc());
		Employee manager=empRepository.findById(projectDto.getProjectManagerId())
				.orElseThrow(()->  new ExceptionTemplate("manager not found", HttpStatus.NOT_FOUND));
		project.setProjectManager(manager);
		Set<Employee> teamMembers=project.getEmployees();
		for(long id:projectDto.getEmployeeId()) {
			Employee emp=findEmployee(id);
			teamMembers.add(emp);
		}
		project.setEmployees(teamMembers);
		return project;
	}
	
	
	
	private Employee findEmployee(Long id) {
		return empRepository.findById(id).orElseThrow(()-> new ExceptionTemplate(" employee not found", HttpStatus.NOT_FOUND));
	}
	

}
