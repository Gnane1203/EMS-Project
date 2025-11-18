package com.ems.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.management.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	// find by email (for login)
    Optional<Employee> findByEmail(String email);

    // find employees by their manager
    List<Employee> findByprimaryManager_empId(Long managerId);

    // find by role (optional, if needed for admin filtering)
    List<Employee> findByRole_roleName(String roleName);
    
    // check email is exists or not
    public boolean existsByEmail(String email);
}
