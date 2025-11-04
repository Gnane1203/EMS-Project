package com.ems.management.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ems.management.models.Employee;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.service.EmployeeService;


public class EmployeeServiceImpl implements EmployeeService, UserDetailsService{
	
	@Autowired
    private EmployeeRepository repo;

	//create employee
	
    @Override
    public Employee register(Employee employee) {
    	
        return repo.save(employee);
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
    @Override
    public Employee updateEmployee(Employee employee) {
    	
        return repo.save(employee);
    }
    
    //delete an  employee
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

}
