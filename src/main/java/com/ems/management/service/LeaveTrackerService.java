package com.ems.management.service;

import java.util.List;
import java.util.Optional;

import com.ems.management.models.LeaveTracker;

public interface LeaveTrackerService {

    LeaveTracker createLeaveTracker(LeaveTracker tracker);

    Optional<LeaveTracker> getTrackerByEmployeeId(Long empId);

    List<LeaveTracker> getAllTrackers();

    LeaveTracker updateLeaveBalance(Long empId, int usedLeaves);

    void deleteLeaveTracker(Long id);
}
