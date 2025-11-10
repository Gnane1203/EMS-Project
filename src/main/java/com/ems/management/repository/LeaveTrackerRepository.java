package com.ems.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.management.models.LeaveTracker;

public interface LeaveTrackerRepository extends JpaRepository<LeaveTracker, Long>{

	// find leave tracker for a specific employee
    Optional<LeaveTracker> findByEmployee_empId(Long employeeId);
}
