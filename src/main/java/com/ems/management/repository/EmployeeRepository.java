package com.ems.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.management.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	// find by email (for login)
    Optional<Employee> findByEmail(String email);

    // find employees by their manager
    List<Employee> findByManagerId(Long managerId);

    // find by role (optional, if needed for admin filtering)
    List<Employee> findByRole(String role);
}
