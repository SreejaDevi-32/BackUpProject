package com.virtusa.telecom.user.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.telecom.user.user_service.dto.AuthResponse;
import com.virtusa.telecom.user.user_service.dto.UserLoginRequest;
import com.virtusa.telecom.user.user_service.dto.UserRegistrationRequest;
import com.virtusa.telecom.user.user_service.dto.UserRegistrationResponse;
import com.virtusa.telecom.user.user_service.service.UserService;
import com.virtusa.telecom.user.user_service.systemconstants.UserConstants;
import com.virtusa.telecom.utility.commonlib.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserRegistrationResponse>> register(@Valid @RequestBody UserRegistrationRequest request) 
	{
		userService.registerUser(request);
		UserRegistrationResponse response = new UserRegistrationResponse(UserConstants.STATUS_201,UserConstants.MESSAGE_201);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(response));
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLoginRequest request)
	{
		AuthResponse response = userService.login(request);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
							.body(response);
		
	}
	

}
