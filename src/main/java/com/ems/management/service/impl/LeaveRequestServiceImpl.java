package com.ems.management.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ems.management.models.Employee;
import com.ems.management.models.LeaveRequest;
import com.ems.management.models.LeaveTracker;
import com.ems.management.models.OvertimeTracker;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.repository.LeaveRequestRepository;
import com.ems.management.repository.LeaveTrackerRepository;
import com.ems.management.repository.OvertimeTrackerRepository;
import com.ems.management.service.LeaveRequestService;

import jakarta.transaction.Transactional;

@Service
public class LeaveRequestServiceImpl  implements LeaveRequestService{

	@Autowired
    private LeaveRequestRepository repo;
	
	@Autowired
    private EmployeeRepository employeeRepo;
	
	@Autowired
	private OvertimeTrackerRepository overtimeRepo;
	
	private LeaveTrackerRepository leaveTrackerRepo;
	
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
        
        Employee emp = leave.getEmployee();
     // ✅ Only deduct overtime if this is a COMPOFF leave and it's APPROVED
        if (status.equalsIgnoreCase("APPROVED")) {
        	
        
            if(leave.getLeaveType().equalsIgnoreCase("COMPOFF")) {

            

            // Fetch all unused overtime entries for that employee
            List<OvertimeTracker> unused = overtimeRepo.findByEmployeeEmpIdAndUsedFalse(emp.getEmpId());

            float totalAvailable = (float) unused.stream()
                    .mapToDouble(OvertimeTracker::getOvertimeHours)
                    .sum();

            if (totalAvailable < 8) {
                throw new RuntimeException("Insufficient overtime hours. Cannot approve comp-off.");
            }

            // ✅ Deduct 8 hours worth of overtime
            float remaining = 8;
            for (OvertimeTracker ot : unused) {
                if (remaining <= 0) break;

                if (ot.getOvertimeHours() <= remaining) {
                    remaining -= ot.getOvertimeHours();
                    ot.setUsed(true);
                    ot.setOvertimeHours(0);
                } else {
                    ot.setOvertimeHours(ot.getOvertimeHours() - remaining);
                    remaining = 0;
                }

                overtimeRepo.save(ot);
            }
        }
     
     //  2. Handle regular leave types
        else {
        	LeaveTracker tracker = leaveTrackerRepo.findByEmployeeId(emp.getEmpId())
                    .orElseThrow(() -> new RuntimeException("Leave tracker not found for employee"));

            switch (leave.getLeaveType().toUpperCase()) {
                case "CASUAL":
                    if (tracker.getCasualLeaves() < leave.getNoOfDays())
                        throw new RuntimeException("Insufficient casual leaves.");
                    tracker.setCasualLeaves(tracker.getCasualLeaves() - leave.getNoOfDays());
                    break;

                case "SICK":
                    if (tracker.getSickLeaves() < leave.getNoOfDays())
                        throw new RuntimeException("Insufficient sick leaves.");
                    tracker.setSickLeaves(tracker.getSickLeaves() - leave.getNoOfDays());
                    break;

                case "MATERNITY":
                    if (tracker.getMaternityLeaves() < leave.getNoOfDays())
                        throw new RuntimeException("Insufficient maternity leaves.");
                    tracker.setMaternityLeaves(tracker.getMaternityLeaves() - leave.getNoOfDays());
                    break;
            }

            tracker.setUsedLeaves(tracker.getUsedLeaves() + leave.getNoOfDays());
            leaveTrackerRepo.save(tracker);
        }
        
       
        } 
        
        return repo.save(leave);
    }
    
    //  Get leave Requests assigned to manager
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
