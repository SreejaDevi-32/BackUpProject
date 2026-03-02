package com.virtusa.telecom.subscription.subscription_service.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtusa.telecom.utility.commonlib.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error ->
                  fieldErrors.put(error.getField(), error.getDefaultMessage())
          );

        log.warn("Validation failed for {} : {}", request.getRequestURI(), fieldErrors);

        ErrorResponse response = new ErrorResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_FAILED",
                "Request contains invalid fields",
                LocalDateTime.now(),
                fieldErrors
        );

        return ResponseEntity.badRequest().body(response);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(
	        ConstraintViolationException ex,
	        HttpServletRequest request) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getConstraintViolations().forEach(violation ->
	            errors.put(
	                violation.getPropertyPath().toString(),
	                violation.getMessage()
	            )
	    );
	    return ResponseEntity.badRequest().body(
	            new ErrorResponse(
	                    request.getRequestURI(),
	                    HttpStatus.BAD_REQUEST.value(),
	                    "VALIDATION_FAILED",
	                    "Invalid request parameters",
	                    LocalDateTime.now(),
	                    errors
	            )
	    );
	}
	
	
	@ExceptionHandler(ActiveSubscriptionAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleActiveSubscription(
            ActiveSubscriptionAlreadyExistsException ex,HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse(
				request.getRequestURI(),
				HttpStatus.CONFLICT.value(),
				"CONFLICT_ERROR",
				ex.getMessage(),
				LocalDateTime.now(),
				null
				);
		
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(response);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex,HttpServletRequest request) {
		log.error("User Not Found {}",ex);
		
		ErrorResponse response = new ErrorResponse(
				request.getRequestURI(),
				HttpStatus.NOT_FOUND.value(),
				"NOT_FOUND",
				ex.getMessage(),
				LocalDateTime.now(),
				null
				);
		
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 409
                .body(response);
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request)
	{
		log.error("Unexpected Error at {}",request.getRequestURI(),ex);
		
		ErrorResponse response = new ErrorResponse(
				request.getRequestURI(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"INTERNAL_SERVER_ERROR",
                "Something went wrong. Please contact support.",
                LocalDateTime.now(),
                null
				);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		
	}

	
	

}
