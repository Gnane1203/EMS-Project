package com.ems.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.management.dto.request.EmployeeAddDto;
import com.ems.management.dto.response.ApiResponse;
import com.ems.management.dto.response.EmployeeResponseDto;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.models.Employee;
import com.ems.management.service.EmployeeService;

@RequestMapping("/admin")
@RestController
public class EmployeeContoller {
	@Autowired
	private EmployeeService empservice;
	
	
	@PostMapping("/employee")
	public ResponseEntity<ApiResponse<Long>> saveEmployee(@RequestBody EmployeeAddDto emp){
		try {
			Employee emp1 =empservice.register(emp);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ApiResponse.success("employee successfully created", emp1.getEmpId()));
		} catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), (long)0));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), (long)0));
		}
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<ApiResponse<String>> updateEmployee(@PathVariable Long id, @RequestBody EmployeeAddDto emp){
		try {
			empservice.updateEmployee(id, emp);
			return ResponseEntity.status(HttpStatus.OK)
					.body(ApiResponse.success("employee successfully updated", "id is "+id));
		} catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), e.getMessage()));
		}
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), "Internal server error"));
		}
		
	}
	
	
	@GetMapping("/employee/id/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponseDto>> findEmployee(@RequestBody Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success("employee successfully updated", empservice.findbyIdResponse(id)));
		} catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
	}
	@GetMapping("/employee/email/{email}")
	public ResponseEntity<ApiResponse<EmployeeResponseDto>> findEmployeeEmail(@RequestBody String email){
		try {
			return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success("employee successfully updated", empservice.findbyIdResponse(email)));
		} catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), null));
		}
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), null));
		}
	}
	
	@GetMapping("/employee")
	public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> findAllEmployeess(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success("employee successfully updated", empservice.getAllEmployees()));
	}
	
	@DeleteMapping("/employee/id/{id}")
	public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable Long id, @RequestBody EmployeeAddDto emp){
		try {
			empservice.deleteEmployee(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(ApiResponse.success("employee deleted successfully ", "id is "+id));
		} catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), e.getMessage()));
		}
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), "Internal server error"));
		}
		
	}
	@DeleteMapping("/employee/email/{email}")
	public ResponseEntity<ApiResponse<String>> deleteEmployeeByEmail(@PathVariable String email){
		try {
			empservice.deleteEmployee(email);
			return ResponseEntity.status(HttpStatus.OK)
					.body(ApiResponse.success("employee deleted successfully ", "email is "+email));
		} catch (ExceptionTemplate e) {
			return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.failure(e.getMessage(), e.getMessage()));
		}
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.failure(e.getMessage(), "Internal server error"));
		}
		
	}
	
}
