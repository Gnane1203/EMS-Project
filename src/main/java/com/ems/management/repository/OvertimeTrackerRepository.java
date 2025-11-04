package com.ems.management.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ems.management.models.OvertimeTracker;

public interface OvertimeTrackerRepository extends JpaRepository<OvertimeTracker, Long> {

    List<OvertimeTracker> findByEmployeeEmpId(Long empId);

    List<OvertimeTracker> findByEmployeeEmpIdAndUsedFalse(Long empId);

    float countByEmployeeEmpIdAndUsedFalse(Long empId);

    List<OvertimeTracker> findByEmployeeEmpIdAndDateBetween(Long empId, LocalDate startDate, LocalDate endDate);
}
