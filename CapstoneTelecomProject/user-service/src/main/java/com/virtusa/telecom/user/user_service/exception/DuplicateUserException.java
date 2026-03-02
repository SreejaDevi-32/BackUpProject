package com.virtusa.telecom.user.user_service.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("User already exists with the given email or phone number");
    }

    public DuplicateUserException(String message) {
        super(message);
    }
}

