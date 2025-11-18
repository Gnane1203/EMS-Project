package com.ems.management.mappers;

import java.util.List;
import java.util.Set;

import com.ems.management.dto.response.EmployeeResponseDto;
import com.ems.management.models.Employee;
import com.ems.management.models.Project;

public class EmployeeResponseMapper {
	
	public static EmployeeResponseDto empResponse(Employee emp) {
		EmployeeResponseDto empRes=new EmployeeResponseDto();
		empRes.setEmpId(emp.getEmpId());
		empRes.setEmpName(emp.getEmpName());
		empRes.setEmail(empRes.getEmail());
		empRes.setGender(emp.getGender());
		empRes.setEmpType(emp.getEmpType());
		empRes.setJoiningDate(emp.getJoiningDate());
		empRes.setResignationDate(emp.getResignationDate());
		empRes.setRoleId(emp.getRole().getRoleId());
		empRes.setRoleName(emp.getRole().getRoleName());
		empRes.setPrimaryManagerId(emp.getPrimaryManager().getEmpId());
		empRes.setPrimaryManagerName(emp.getPrimaryManager().getEmpName());
		empRes.setProjectIds(listOfProjects(emp.getProjects()));
		empRes.setActive(emp.getActive());
		empRes.setAdmin(emp.getIsAdmin());
		empRes.setCreatedAt(emp.getCreatedAt().toString());	
		empRes.setUpdatedAt(emp.getUpdatedAt().toString());
		return empRes;
	}
	
	public static List<Long> listOfProjects(Set<Project> set){
		return set.stream().map(Project :: getProjectId).toList();
	}

}
