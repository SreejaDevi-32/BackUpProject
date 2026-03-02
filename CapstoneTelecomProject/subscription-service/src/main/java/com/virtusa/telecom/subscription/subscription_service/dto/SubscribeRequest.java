package com.virtusa.telecom.subscription.subscription_service.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class SubscribeRequest {

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Plan ID is required")
    private UUID planId;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;
}