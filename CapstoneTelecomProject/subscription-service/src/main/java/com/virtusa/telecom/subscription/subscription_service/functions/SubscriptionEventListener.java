package com.virtusa.telecom.subscription.subscription_service.functions;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.virtusa.telecom.subscription.subscription_service.dto.SubscriptionCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubscriptionEventListener {
	
	private final StreamBridge streamBridge;
	
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleAfterCommit(SubscriptionCreatedEvent event)
	{
		streamBridge.send("subscriptionCreated-out-0",event);
	}
}
