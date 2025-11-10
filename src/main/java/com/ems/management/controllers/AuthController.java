package com.ems.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ems.management.dto.request.LoginDTO;
import com.ems.management.dto.response.ApiError;
import com.ems.management.dto.response.ApiResponse;
import com.ems.management.util.JwtService;
@RequestMapping("/auth")
public class AuthController {
	
private AuthenticationManager authManager;
@Autowired
private JwtService jwtService;
	
	public AuthController(@Lazy AuthenticationManager authManager) {
		super();
		this.authManager = authManager;
	}
	
	
	
	/***
	 * @author Sreenivas
	 * @apiNote this API is used for login 
	 * @param data
	 * @return ResponseEntity<ApiResponse<T>>
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO data){
		try {UsernamePasswordAuthenticationToken userToken= 
				new UsernamePasswordAuthenticationToken(data.Username(), data.Password());
		Authentication auth=authManager.authenticate(userToken);
		if(!auth.isAuthenticated())
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.failure("Bad credentials!!",""));
		String jwtToken=jwtService.generateToken(data.Username());
		
		return new ResponseEntity<>(ApiResponse.success("login success fully",jwtToken),HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(
				    ApiError.of(e.getMessage(), "401", "/auth/login"),
				    HttpStatus.UNAUTHORIZED
				);

		}
		
	}
}
