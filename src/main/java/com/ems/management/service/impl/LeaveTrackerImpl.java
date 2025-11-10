package com.ems.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.management.models.LeaveTracker;
import com.ems.management.repository.LeaveTrackerRepository;
import com.ems.management.service.LeaveTrackerService;

import jakarta.transaction.Transactional;

@Service
public class LeaveTrackerImpl implements LeaveTrackerService{

    @Autowired
    private LeaveTrackerRepository leaveTrackerRepository;

    @Override
    public LeaveTracker createLeaveTracker(LeaveTracker tracker) {
        return leaveTrackerRepository.save(tracker);
    }

    @Override
    public Optional<LeaveTracker> getTrackerByEmployeeId(Long empId) {
        return leaveTrackerRepository.findByEmployee_empId(empId);
    }

    @Override
    public List<LeaveTracker> getAllTrackers() {
        return leaveTrackerRepository.findAll();
    }

    @Override
    @Transactional
    public LeaveTracker updateLeaveBalance(Long empId, int usedLeaves) {
        Optional<LeaveTracker> trackerOpt = leaveTrackerRepository.findByEmployee_empId(empId);
        if (trackerOpt.isPresent()) {
            LeaveTracker tracker = trackerOpt.get();
            tracker.setUsedLeaves(tracker.getUsedLeaves() + usedLeaves);
            return leaveTrackerRepository.save(tracker);
        }
        return null;
    }

    @Override
    public void deleteLeaveTracker(Long id) {
        leaveTrackerRepository.deleteById(id);
    }
}
