package com.ems.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.management.models.TimeSheetRequest;

public interface TimeSheetRequestRepository extends JpaRepository<TimeSheetRequest, Long> {

	// find all timesheets for an employee
	List<TimeSheetRequest> findByEmployeeEmpId(Long empId);

	// find by approver (manager)
	List<TimeSheetRequest> findByApproverEmpId(Long approverId);

	// to list all the timesheet of an employee based on status
	List<TimeSheetRequest> findByEmployeeEmpIdAndStatus(Long empId, String status);

	// to list all the timesheets of employees based on status
	List<TimeSheetRequest> findByStatus(String status);

	// list all employees find by Approver id
	List<TimeSheetRequest> findByApprover_EmpId(Long approverId);

	// ✔ If you also want to filter by status 
	List<TimeSheetRequest> findByApprover_EmpIdAndStatus(Long approverId, String status);

}
