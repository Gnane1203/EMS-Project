package com.ems.management.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ems.management.models.Employee;
import com.ems.management.models.LeaveRequest;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.repository.LeaveRequestRepository;
import com.ems.management.service.LeaveRequestService;

import jakarta.transaction.Transactional;

@Service
public class LeaveRequestServiceImpl  implements LeaveRequestService{

	@Autowired
    private LeaveRequestRepository repo;
	
	@Autowired
    private EmployeeRepository employeeRepo;
	
	@Transactional
    @Override
    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
    	 // Auto-set approver based on assigned manager
    	
    	// Fetch the full Employee entity from DB (with manager info)
        Employee emp = employeeRepo.findById(leaveRequest.getEmployee().getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Now you can safely access the manager from that entity
        if (emp.getPrimaryManager() != null) {
            leaveRequest.setApprover(emp.getPrimaryManager());
        }
    	   	
        leaveRequest.setStatus("PENDING");
        
        return repo.save(leaveRequest);
    }

    //  Manager approves or rejects leave
	@Transactional
    @Override
    public LeaveRequest updateLeaveStatus(Long leaveId, String status, String mgrComments) {
        LeaveRequest leave = repo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

       
        leave.setStatus(status);
        leave.setMgrComments(mgrComments);
        return repo.save(leave);
    }
    
    //  Get leaves assigned to manager
    @Override
    public List<LeaveRequest> getLeavesByManager(Long managerId) {
        return repo.findByApproverEmpId(managerId);
    }
    
    //  Get pending leaves for manager
    @Override
    public List<LeaveRequest> getPendingLeaves(Long managerId) {
        return repo.findByApproverEmpIdAndStatus(managerId, "PENDING");
    }

    @Override
    public List<LeaveRequest> getAllLeaves() {
        return repo.findAll();
    }

    @Override
    public List<LeaveRequest> getLeavesByEmployee(Long empId) {
        return repo.findByEmployeeEmpId(empId);
    }
}
