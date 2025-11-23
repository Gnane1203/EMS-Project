package com.ems.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.management.dto.request.ProjectAddDto;
import com.ems.management.dto.response.ApiResponse;
import com.ems.management.dto.response.ProjectResDto;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
	
	@Autowired
	private ProjectService projectServise;
	@PostMapping
	public ResponseEntity<ApiResponse<ProjectResDto>> createProject(@RequestBody ProjectAddDto projectDto){
		try {
			ProjectResDto pDto=projectServise.createProject(projectDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.failure("project created successfully", pDto));
		}catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
		
	}
	@PutMapping("/{pId}")
	public ResponseEntity<ApiResponse<ProjectResDto>> updateProject(@PathVariable Long pId ,@RequestBody ProjectAddDto projectDto){
		try {
			ProjectResDto pDto=projectServise.updateProject(pId,projectDto);
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.failure("project created successfully", pDto));
		}catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
		
	}
	@GetMapping("/{pId}")
	public ResponseEntity<ApiResponse<ProjectResDto>> getProject(@PathVariable Long pId ){
		try {
			ProjectResDto pDto=projectServise.getProjectResById(pId);
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.failure("project created successfully", pDto));
		}catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
		
	}
	@DeleteMapping("/{pId}")
	public ResponseEntity<ApiResponse<String>> updateProject(@PathVariable Long pId ){
		try {
			projectServise.deleteProject(pId);
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.failure("project created successfully", "Deleted project Id "+pId));
		}catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
		
	}
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProjectResDto>>> getAllProject(){
		try {
			List<ProjectResDto> pDto=projectServise.getAllProjectDtos();
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.failure("project created successfully", pDto));
		}catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
		
	}
	@GetMapping("/manager/{managerId}/search")
	@Operation(
		    summary = "Search projects of a specific manager",
		    description = "Returns a filtered and sorted list of projects managed by the given manager. "
		                + "You can search using a keyword and control sorting direction (asc/desc)."
		                + "🔹 Search projects of a specific manager\r\n"
		                + "GET /api/v1/projects/manager/5/search?keyword=crm&sortDir=desc"
		)
	public ResponseEntity<ApiResponse<Page<ProjectResDto>>> searchManagerProjects(
            @PathVariable Long managerId,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "projectId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
		try {
			Page<ProjectResDto> pDto=projectServise.searchProjectsByManager(managerId, keyword, page, size, sortBy, sortDir);
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.failure("project created successfully", pDto));
		}catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
		
	}
	

}
