package com.ems.management.service;

import java.util.List;

import com.ems.management.models.TimeSheetRequest;

public interface TimeSheetRequestService {

	// Employee submits a new timesheet
    TimeSheetRequest submitTimeSheet(TimeSheetRequest timeSheetRequest);

    // Employee views their own timesheets
    List<TimeSheetRequest> getEmployeeTimeSheets(Long empId);

    // Employee filters timesheets by status
    List<TimeSheetRequest> getEmployeeTimeSheetsByStatus(Long empId, String status);

    // Manager views pending or approved/rejected timesheets of employees
    List<TimeSheetRequest> getManagerTimeSheets(Long managerId);

    // Admin views timesheets across all employees by status
    List<TimeSheetRequest> getAllTimeSheetsByStatus(String status);

    // Manager approves a timesheet
    TimeSheetRequest approveTimeSheet(Long sheetId, String mgrComments);

    // Manager rejects a timesheet
    TimeSheetRequest rejectTimeSheet(Long sheetId, String mgrComments);
}
