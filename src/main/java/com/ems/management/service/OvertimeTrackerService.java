package com.ems.management.service;



import com.ems.management.models.OvertimeTracker;

public interface OvertimeTrackerService {
   // Get total unused overtime hours
    float getAvailableOvertimeHours(Long empId);

    // Mark overtime as used (for comp-off)
    void markOvertimeAsUsed(Long empId, float hoursToDeduct);
}
