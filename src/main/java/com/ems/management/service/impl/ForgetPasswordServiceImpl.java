package com.ems.management.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.management.dto.request.LoginDTO;
import com.ems.management.exeption.ExceptionTemplate;
import com.ems.management.models.Employee;

import com.ems.management.otp.service.ForgetPasswordService;
import com.ems.management.repository.EmployeeRepository;
@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public String forgetPassword(LoginDTO data) {
		Optional<Employee> optEmployee = employeeRepository.findByEmail(data.Username());
		if(optEmployee.isEmpty()) 
			throw new ExceptionTemplate("email not found", HttpStatus.NOT_FOUND);
		Employee employee=optEmployee.get();
		String hashPassword= encoder.encode(data.Password());
		employee.setPasswordHash(hashPassword);
		employeeRepository.save(employee);
		
		return " password updated successfully" ;
	}
	
	
	
	
	

}
