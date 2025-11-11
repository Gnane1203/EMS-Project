package com.ems.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.management.models.OvertimeTracker;
import com.ems.management.repository.OvertimeTrackerRepository;
import com.ems.management.service.OvertimeTrackerService;

import jakarta.transaction.Transactional;

@Service
public class OvertimeTrackerServiceImpl implements OvertimeTrackerService{
	   @Autowired
	    private OvertimeTrackerRepository overtimeRepo;



	    @Override
	    public float getAvailableOvertimeHours(Long empId) {
	        List<OvertimeTracker> unused = overtimeRepo.findByEmployeeEmpIdAndUsedFalse(empId);
	        return (float) unused.stream()
	                .mapToDouble(OvertimeTracker::getOvertimeHours)
	                .sum();
	    }

	    @Override
	    @Transactional
	    public void markOvertimeAsUsed(Long empId, float hoursToDeduct) {
	        List<OvertimeTracker> unused = overtimeRepo.findByEmployeeEmpIdAndUsedFalse(empId);

	        float remaining = hoursToDeduct;
	        for (OvertimeTracker ot : unused) {
	            if (remaining <= 0) break;

	            if (ot.getOvertimeHours() <= remaining) {
	                remaining -= ot.getOvertimeHours();
	                ot.setOvertimeHours(0);
	                ot.setUsed(true);
	            } else {
	                ot.setOvertimeHours(ot.getOvertimeHours() - remaining);
	                remaining = 0;
	            }

	            overtimeRepo.save(ot);
	        }
	    }
}
