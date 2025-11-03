package com.ems.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.management.models.LeaveRequest;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{
	
	// Fetch all leave requests submitted by a specific employee
    List<LeaveRequest> findByEmployeeEmpId(Long empId);

    // Fetch all leave requests assigned to a manager (approver)
    List<LeaveRequest> findByApproverEmpId(Long approverId);

    // Fetch leaves by status (PENDING / APPROVED / REJECTED)
    List<LeaveRequest> findByStatus(String status);

    // Fetch all pending leaves that need manager's action
    List<LeaveRequest> findByApproverEmpIdAndStatus(Long approverId, String status);
}
