package com.ems.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PaswwordController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
			
	@GetMapping("/auth/encode/{password}")
	public String encodePassword(@PathVariable String password) {
	    return new BCryptPasswordEncoder().encode(password);
	}

}
