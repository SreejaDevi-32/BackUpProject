package com.virtusa.telecom.subscription.subscription_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public record SubscriptionCreatedEvent(
		 UUID subscriptionId,
	        UUID userId,
	        BigDecimal amount,
	        String currency,
	        String sagaId,
	        String emailId,
	        LocalDateTime createdAt
		) {

}
