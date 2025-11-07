package com.ems.management.controllers;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ems.management.dto.request.LoginDTO;
import com.ems.management.dto.response.ApiResponse;
@RequestMapping("/auth")
public class AuthController {
	
private AuthenticationManager authManager;
	
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
	public ResponseEntity<ApiResponse<String>> login(LoginDTO data){
		UsernamePasswordAuthenticationToken userToken= 
				new UsernamePasswordAuthenticationToken(data.Username(), data.Password());
		Authentication auth=authManager.authenticate(userToken);
		if(!auth.isAuthenticated())
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.failure("Bad credentials!!",""));
		
		return new ResponseEntity<>(new ApiResponse<String>(null, null, null, null),HttpStatus.OK);
	}
}
