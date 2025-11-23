package com.ems.management.mappers;



import java.util.LinkedHashSet;

import java.util.stream.Collectors;


import com.ems.management.dto.request.ProjectAddDto;
import com.ems.management.dto.response.ProjectResDto;
import com.ems.management.models.Employee;
import com.ems.management.models.Project;


public class ProjectMapper {

    public static Project toEntity(ProjectAddDto dto) {
        Project p = new Project();
        p.setProjectName(dto.getProjectName());
        p.setProjectDesc(dto.getProjectDesc());
        
        return p;
    }

    public static ProjectResDto toResDto(Project project) {
        ProjectResDto dto = new ProjectResDto();
        dto.setProjectId(project.getProjectId());
        dto.setProjectName(project.getProjectName());
        dto.setProjectDesc(project.getProjectDesc());
        dto.setProjectManagerId(project.getProjectManager().getEmpId());
        dto.setProjectManagerName(project.getProjectManager().getEmpName());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());
        dto.setEmployeeId(
                project.getEmployees()
                        .stream()
                        .map(Employee::getEmpId)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );

        return dto;
    }
}
