package com.ems.management.service;

import java.util.List;
import java.util.Optional;

import com.ems.management.dto.request.EmployeeAddDto;
import com.ems.management.dto.response.EmployeeResponseDto;
import com.ems.management.models.Employee;

public interface EmployeeService {
	
	//create employee
	Employee register(EmployeeAddDto employee);
	
	//find an employee or get
    Optional<Employee> findByEmail(String email);
    
    //get all list of employee
    List<EmployeeResponseDto> getAllEmployees();
    
    //update an employee
    Employee updateEmployee( Long id,EmployeeAddDto employee);
    
    //delete an  employee
    boolean deleteEmployee(Long id);
    void deleteEmployeebyid(Long id);
    void deleteEmployee(String email);
    
    //find by id
    public Employee getById(Long id);
    public EmployeeResponseDto findbyIdResponse(Long id);
    public EmployeeResponseDto findbyIdResponse(String email);

}
