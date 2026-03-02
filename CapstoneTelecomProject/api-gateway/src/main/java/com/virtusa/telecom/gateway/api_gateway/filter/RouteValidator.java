package com.virtusa.telecom.gateway.api_gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
public class RouteValidator {

    // ✅ Public endpoints (No token required)
    public static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/v1/users/register",
            "/api/v1/users/login",
            "/eureka"
    );

    // ✅ Admin-only endpoints
    public static final List<String> ADMIN_ENDPOINTS = List.of(
            "/api/v1/plans/create"
    );

    // Check if request is secured
    public Predicate<ServerHttpRequest> isSecured =
            request -> PUBLIC_ENDPOINTS.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    // Check if request requires ADMIN role
    public Predicate<ServerHttpRequest> isAdminRoute =
            request -> ADMIN_ENDPOINTS.stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));
}

