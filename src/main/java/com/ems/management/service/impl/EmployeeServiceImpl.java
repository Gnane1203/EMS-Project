package com.ems.management.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ems.management.dto.request.EmployeeAddDto;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.mappers.EmployeeModelMapper;
import com.ems.management.models.Employee;
import com.ems.management.models.LeaveTracker;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.repository.LeaveTrackerRepository;
import com.ems.management.service.EmployeeService;

import jakarta.transaction.Transactional;


@Service

public class EmployeeServiceImpl implements EmployeeService, UserDetailsService{
	
	@Autowired
    private EmployeeRepository repo;

	@Autowired
	private LeaveTrackerRepository leaveTrackerRepo;
	@Autowired
	private EmployeeModelMapper empMapper;
	
	//create employee
	@Transactional
    @Override
    public Employee register(EmployeeAddDto employee) {
		
		if (repo.findByEmail(employee.getEmail()).isPresent()) {
	        throw new ExceptionTemplate("Email already registered: " + employee.getEmail(),HttpStatus.CONFLICT);
	    }
		Employee employeeModel=empMapper.employeeMapper(employee, new Employee());
		
    	Employee savedEmp = repo.save(employeeModel);
        // Create default LeaveTracker
        LeaveTracker tracker = new LeaveTracker(savedEmp);
        tracker.setCasualLeaves(12);
        tracker.setSickLeaves(10);
        
        //  Assign maternity leave based on gender
        
        if (savedEmp.getGender().equalsIgnoreCase("FEMALE")) {
            tracker.setMaternityLeaves(180); // ~6 months
        } else {
            tracker.setMaternityLeaves(0); // Male employees are not eligible
        }
        
        tracker.setUsedLeaves(0);

        // Save leave tracker record
        leaveTrackerRepo.save(tracker);	
         
        return savedEmp;
    	
    }

    //find an employee or get
    @Override
    public Optional<Employee> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    //get all list of employee
    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    //update an employee
    @Transactional
    @Override
    public Employee updateEmployee(Employee employee) {
    	
    	Employee existing = repo.findById(employee.getEmpId())
    	        .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employee.getEmpId()));

    	    existing.setEmpName(employee.getEmpName());
    	    existing.setEmail(employee.getEmail());
    	    existing.setRole(employee.getRole());
    	    existing.setPrimaryManager(employee.getPrimaryManager());
    	    existing.setUpdatedAt(LocalDateTime.now());
    	    // copy only editable fields, not password or sensitive fields

    	    return repo.save(existing);
    }
    
    //delete an  employee
    @Transactional
    @Override
    public boolean deleteEmployee(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee user = repo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));

		
		return org.springframework.security.core.userdetails.User.builder().username(email).password(user.getPasswordHash())
				.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleName())))
				.build();
	}

	@Override
	public Employee getById(Long id) {
		
		return repo.findById(id).orElseThrow(()-> new ExceptionTemplate("employee Not found", HttpStatus.NOT_FOUND));
	}

}
