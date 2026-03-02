package com.virtusa.telecom.utility.commonlib;

import java.time.LocalDateTime;
import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResponse {

    private String path;
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> validationErrors; // optional
	public ErrorResponse(String path, int status, String error, String message, LocalDateTime timestamp,
			Map<String, String> validationErrors) {
		super();
		this.path = path;
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = LocalDateTime.now();
		this.validationErrors = validationErrors;
	}
    
    
    
}
