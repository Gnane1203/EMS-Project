package com.ems.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.management.dto.response.ApiResponse;
import com.ems.management.models.TimeSheetRequest;
import com.ems.management.service.TimeSheetRequestService;

@RestController
@RequestMapping("/api/v1/time-sheet")
public class TimeSheetController {
	
	@Autowired
	private TimeSheetRequestService tmsService;
	
	
	public ResponseEntity<ApiResponse<TimeSheetRequest>> addTimeSheet(@RequestBody TimeSheetRequest timeSheet){
		TimeSheetRequest tms= tmsService.submitTimeSheet(timeSheet);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Timesheet created", tms));
		
	}
	@GetMapping("/{empId}/emp")
	public ResponseEntity<ApiResponse<List<TimeSheetRequest>>> getTimeSheets(@PathVariable Long empId){
		List<TimeSheetRequest> tms= tmsService.getEmployeeTimeSheets(empId);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Timesheet created", tms));
		
	}
	@GetMapping("/{empId}/emp/{status}")
	public ResponseEntity<ApiResponse<List<TimeSheetRequest>>> getAllTimeSheetByStatus(@PathVariable Long empId, String status){
		List<TimeSheetRequest> tms= tmsService.getEmployeeTimeSheetsByStatus(empId, status);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Timesheet created", tms));	
	}
	@GetMapping("/{mId}/emp")
	public ResponseEntity<ApiResponse<List<TimeSheetRequest>>> getTimeSheetsByManager(@PathVariable Long mId){
		List<TimeSheetRequest> tms= tmsService.getManagerTimeSheets(mId);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Timesheet created", tms));
		
	}
	@GetMapping("/{mId}/emp/{status}")
	public ResponseEntity<ApiResponse<List<TimeSheetRequest>>> getAllTimeSheetByManager(@PathVariable Long mId, String status){
		List<TimeSheetRequest> tms= tmsService.getManagerTimeSheets(mId, status);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Timesheet created", tms));	
	}

}
