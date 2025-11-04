package com.ems.management.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.management.models.Employee;
import com.ems.management.models.OvertimeTracker;
import com.ems.management.models.TimeSheetRequest;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.repository.OvertimeTrackerRepository;
import com.ems.management.repository.TimeSheetRequestRepository;
import com.ems.management.service.TimeSheetRequestService;

import jakarta.transaction.Transactional;

@Service
public class TimeSheetRequestServiceImpl implements TimeSheetRequestService {

    @Autowired
    private TimeSheetRequestRepository timeSheetRepo;

    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private OvertimeTrackerRepository overtimeRepo;

    
    // =============================
    // Employee submits a timesheet
    // =============================
    @Transactional
    @Override
    public TimeSheetRequest submitTimeSheet(TimeSheetRequest timeSheetRequest) {
        // Fetch employee to attach correct manager (approver)
        Employee emp = employeeRepo.findById(timeSheetRequest.getEmployee().getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Set approver as manager of employee
        if (emp.getPrimaryManager() != null) {
            timeSheetRequest.setApprover(emp.getPrimaryManager());
        }
        
        float workedHours = timeSheetRequest.getHoursWorked();
        float overtime = workedHours > 8 ? workedHours - 8 : 0;


        //  Only record overtime if ≥ 4 hours extra
        if (overtime >= 4) {
            OvertimeTracker ot = new OvertimeTracker(emp, timeSheetRequest.getDate(), overtime);
            overtimeRepo.save(ot);
        }
        
        // Set metadata
        timeSheetRequest.setEmployee(emp);
        timeSheetRequest.setStatus("PENDING");

        return timeSheetRepo.save(timeSheetRequest);
    }

    // =============================
    // Get all timesheets for an employee
    // =============================
    @Transactional
    @Override
    public List<TimeSheetRequest> getEmployeeTimeSheets(Long empId) {
        return timeSheetRepo.findByEmployeeEmpId(empId);
    }

    // =============================
    // Get timesheets by status for an employee
    // =============================
    @Override
    public List<TimeSheetRequest> getEmployeeTimeSheetsByStatus(Long empId, String status) {
        return timeSheetRepo.findByEmployeeEmpIdAndStatus(empId, status);
    }

    // =============================
    // Get timesheets assigned to a manager (approver)
    // =============================
    @Override
    public List<TimeSheetRequest> getManagerTimeSheets(Long managerId) {
        return timeSheetRepo.findByApproverEmpId(managerId);
    }

    // =============================
    // Get all timesheets by status (Admin view)
    // =============================
    @Override
    public List<TimeSheetRequest> getAllTimeSheetsByStatus(String status) {
        return timeSheetRepo.findByStatus(status);
    }

    // =============================
    // Approve a timesheet
    // =============================
    @Transactional
    @Override
    public TimeSheetRequest approveTimeSheet(Long sheetId, String mgrComments) {
        TimeSheetRequest ts = timeSheetRepo.findById(sheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found"));

        ts.setStatus("APPROVED");
        ts.setMgrComments(mgrComments);
        ts.setApproverDate(LocalDateTime.now());


        return timeSheetRepo.save(ts);
    }

    // =============================
    // Reject a timesheet
    // =============================
    @Transactional
    @Override
    public TimeSheetRequest rejectTimeSheet(Long sheetId, String mgrComments) {
        TimeSheetRequest ts = timeSheetRepo.findById(sheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found"));

        ts.setStatus("REJECTED");
        ts.setMgrComments(mgrComments);
        ts.setApproverDate(LocalDateTime.now());
        return timeSheetRepo.save(ts);
    }
}
