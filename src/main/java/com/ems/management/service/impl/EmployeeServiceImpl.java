package com.ems.management.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
import com.ems.management.dto.response.EmployeeResponseDto;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.mappers.EmployeeModelMapper;
import com.ems.management.mappers.EmployeeResponseMapper;
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
    public List<EmployeeResponseDto> getAllEmployees() {
        return repo.findAll().stream()
        		.filter(Objects::nonNull)
        		.map(EmployeeResponseMapper::empResponse)
        		.toList();
    }

    //update an employee
    @Transactional
    @Override
    public Employee updateEmployee(Long id, EmployeeAddDto employee) {
    	
    	Employee existing = repo.findById(id)
    	        .orElseThrow(() -> new ExceptionTemplate("Employee not found with ID: " + id , HttpStatus.NOT_FOUND));

    	    Employee updatedEmployee=empMapper.employeeMapper(employee, existing);

    	    return repo.save(updatedEmployee);
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

	@Override
	public EmployeeResponseDto findbyIdResponse(Long id) {
		Employee employee =getById(id);
		
		return EmployeeResponseMapper.empResponse(employee);
	}

	@Override
	public void deleteEmployee(String email) {
		findByEmail(email);
		repo.deleteByEmail(email);
		
	}

	@Override
	public EmployeeResponseDto findbyIdResponse(String email) {
		Employee emp= findByEmail(email)
				.orElseThrow(()-> new ExceptionTemplate("employee not found", HttpStatus.NOT_FOUND));
		
		return EmployeeResponseMapper.empResponse(emp);
	}

	@Override
	public void deleteEmployeebyid(Long id) {
		getById(id);
		repo.deleteById(id);
		
	}

}
