package com.virtusa.telecom.subscription.subscription_service.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRollbackEvent {
	private UUID subscriptionId;
    private String reason;
    private String sagaId;

}
