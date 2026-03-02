package com.virtusa.telecom.gateway.api_gateway.filter;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.virtusa.telecom.gateway.api_gateway.util.JwtUtil;

import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationGlobalFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;
    private final RouteValidator routeValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        // 1️⃣ Allow public routes
        if (!routeValidator.isSecured.test(request)) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = jwtUtil.validateAndExtractClaims(token);

            if (jwtUtil.isTokenExpired(claims)) {
                return unauthorized(exchange);
            }

            String username = jwtUtil.getUsername(claims);
            List<String> roles = jwtUtil.getRoles(claims);

            // 2️⃣ Check ADMIN routes
            if (routeValidator.isAdminRoute.test(request)
                    && (roles == null || !roles.contains("ROLE_ADMIN"))) {
                return forbidden(exchange);
            }

            // 3️⃣ Add headers to downstream services
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(r -> r.headers(headers -> {
                        headers.add("X-User-Name", username);
                        headers.add("X-User-Roles", String.join(",", roles));
                    }))
                    .build();

            return chain.filter(modifiedExchange);

        } catch (Exception e) {
            return unauthorized(exchange);
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

	/*
	 * private Mono<Void> forbidden(ServerWebExchange exchange) {
	 * exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN); return
	 * exchange.getResponse().setComplete(); }
	 */
    
    private Mono<Void> forbidden(ServerWebExchange exchange) {
        return writeErrorResponse(
                exchange,
                HttpStatus.FORBIDDEN,
                "You do not have permission to access this resource"
        );
    }


    @Override
    public int getOrder() {
        return -1;
    }
    
    private Mono<Void> writeErrorResponse(ServerWebExchange exchange,
            HttpStatus status,
            String message) {

    			exchange.getResponse().setStatusCode(status);
    			exchange.getResponse().getHeaders()
    									.set(HttpHeaders.CONTENT_TYPE, "application/json");

    			String body = String.format("""
    					{
    					"timestamp": "%s",
    					"status": %d,
    					"error": "%s",
    					"message": "%s",
    					"path": "%s"
    					}
    					""",
    					java.time.LocalDateTime.now(),
    					status.value(),
    					status.getReasonPhrase(),
    					message,
    					exchange.getRequest().getURI().getPath()
    					);

	byte[] bytes = body.getBytes(java.nio.charset.StandardCharsets.UTF_8);
	
	return exchange.getResponse()
	.writeWith(Mono.just(
	exchange.getResponse()
	  .bufferFactory()
	  .wrap(bytes)
	));
}

    
}
