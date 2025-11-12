package com.ems.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.management.dto.request.LoginDTO;
import com.ems.management.dto.request.LoginOtpDTO;
import com.ems.management.dto.response.ApiError;
import com.ems.management.dto.response.ApiResponse;
import com.ems.management.otp.service.OtpService;
import com.ems.management.util.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthenticationManager authManager;
	@Autowired
	private JwtService jwtService;

	public AuthController(@Lazy AuthenticationManager authManager) {
		super();
		this.authManager = authManager;
	}

	@Autowired
	private OtpService otpService;

	/***
	 * @author Sreenivas
	 * @apiNote this API is used for login
	 * @param data
	 * @return ResponseEntity<ApiResponse<T>>
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO data) {
		try {
			UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(data.Username(),
					data.Password());
			Authentication auth = authManager.authenticate(userToken);
			if (!auth.isAuthenticated())
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(ApiResponse.failure("Bad credentials!!", ""));
			String jwtToken = jwtService.generateToken(data.Username());

			return new ResponseEntity<>(ApiResponse.success("login success fully", jwtToken), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(ApiError.of(e.getMessage(), "401", "/auth/login"), HttpStatus.UNAUTHORIZED);

		}

	}

	/**
	 * otp sent
	 */
	@PostMapping("/otp/{email}")
	public ResponseEntity<ApiResponse<String>> sendOtp(@PathVariable String email) {
		try {
			String message = otpService.sendOtp(email);
			return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("sent otp successfully", message));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(ApiResponse.failure(e.getMessage(), e.getMessage()));
		}

	}
	/** 
	 * login with otp
	 * 
	 */
	@PostMapping("/otp")
	public ResponseEntity<ApiResponse<String>> otpLogin(@RequestBody LoginOtpDTO details){
		try {
			boolean flag=otpService.validOtp(details);
			if(!flag) throw new Exception("invalid Otp");
			String jwtToken = jwtService.generateToken(details.Username());

			return new ResponseEntity<>(ApiResponse.success("login success fully", jwtToken), HttpStatus.OK);

			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ApiResponse.failure(e.getMessage(), e.getMessage()));
		}
	}
}
