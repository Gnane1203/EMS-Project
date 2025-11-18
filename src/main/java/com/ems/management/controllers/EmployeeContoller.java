package com.ems.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.management.dto.request.EmployeeAddDto;
import com.ems.management.dto.response.ApiResponse;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.models.Employee;
import com.ems.management.service.EmployeeService;

@RestController
public class EmployeeContoller {
	@Autowired
	private EmployeeService empservice;
	
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

}
