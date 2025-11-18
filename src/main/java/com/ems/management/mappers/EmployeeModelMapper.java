package com.ems.management.mappers;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ems.management.dto.request.EmployeeAddDto;
import com.ems.management.models.Employee;
import com.ems.management.models.Project;
import com.ems.management.models.Role;
import com.ems.management.service.EmployeeService;
import com.ems.management.service.ProjectService;
import com.ems.management.service.RoleService;

@Component
public class EmployeeModelMapper {
	@Autowired
	private RoleService roleService;
	@Autowired
	private ProjectService projectService;
	private EmployeeService employeeService;
	
	public EmployeeModelMapper(@Lazy EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
	
	public  Employee employeeMapper(EmployeeAddDto edto,Employee employee) {
		
		
		employee.setEmpName(edto.getEmpName());
		employee.setEmail(edto.getEmail());
		employee.setGender(edto.getGender());
		String password= new BCryptPasswordEncoder().encode(edto.getPasswordHash());
		employee.setPasswordHash(password);
		employee.setEmpType(edto.getEmpType());
		employee.setResignationDate(edto.getResignationDate());
		employee.setJoiningDate(edto.getJoiningDate());
		
		employee.setActive(edto.getActive());
		employee.setIsAdmin(edto.getIsAdmin());
		//find role and update it
		Role role=roleService.getRoleById(edto.getRole());
		employee.setRole(role);
		
		// find project Manager and update
		
		Employee emp=employeeService.getById(edto.getPrimaryManager());
		employee.setPrimaryManager(emp);
		
		//find project and update it
		Set<Project> empProjects= employee.getProjects();
		for(Long id: edto.getProjects()) {
			Project  project =projectService.getProjectById(id);
			empProjects.add(project);
			
		}
		employee.setProjects(empProjects);
		
		
		
		return employee;
	}

}
