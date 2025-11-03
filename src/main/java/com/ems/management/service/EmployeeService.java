package com.ems.management.service;

import java.util.List;
import java.util.Optional;

import com.ems.management.models.Employee;

public interface EmployeeService {
	
	//create employee
	Employee register(Employee employee);
	
	//find an employee or get
    Optional<Employee> findByEmail(String email);
    
    //get all list of employee
    List<Employee> getAllEmployees();
    
    //update an employee
    Employee updateEmployee(Employee employee);
    
    //delete an  employee
    boolean deleteEmployee(Long id);

}
