package com.virtusa.telecom.user.user_service.service;

import com.virtusa.telecom.user.user_service.dto.AuthResponse;
import com.virtusa.telecom.user.user_service.dto.UserLoginRequest;
import com.virtusa.telecom.user.user_service.dto.UserRegistrationRequest;
import com.virtusa.telecom.user.user_service.entity.User;

public interface UserService {
	
	public User registerUser(UserRegistrationRequest request);
	public AuthResponse login(UserLoginRequest request);

}
