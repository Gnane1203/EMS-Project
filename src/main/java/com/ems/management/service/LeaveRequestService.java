package com.ems.management.service;

import java.util.List;

import com.ems.management.models.LeaveRequest;

public interface LeaveRequestService {

	// Employee applies for leave
    LeaveRequest applyLeave(LeaveRequest leaveRequest);

    // Manager approves or rejects the leave
    LeaveRequest updateLeaveStatus(Long leaveId, String status, String mgrComments);

    // Get leaves applied by an employee
    List<LeaveRequest> getLeavesByEmployee(Long empId);

    // Get leaves for a specific manager
    List<LeaveRequest> getLeavesByManager(Long managerId);

    // Get pending leaves for manager
    List<LeaveRequest> getPendingLeaves(Long managerId);

    // Get all leaves (admin view)
    List<LeaveRequest> getAllLeaves();
}
