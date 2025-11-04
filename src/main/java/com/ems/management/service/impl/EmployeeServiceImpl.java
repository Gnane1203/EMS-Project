package com.ems.management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.management.models.Employee;
import com.ems.management.repository.EmployeeRepository;
import com.ems.management.service.EmployeeService;

import jakarta.transaction.Transactional;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
    private EmployeeRepository repo;

	//create employee
	@Transactional
    @Override
    public Employee register(Employee employee) {
		if (repo.findByEmail(employee.getEmail()).isPresent()) {
	        throw new RuntimeException("Email already registered: " + employee.getEmail());
	    }
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

}
