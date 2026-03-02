package com.virtusa.telecom.subscription.subscription_service.exception;

import java.util.UUID;

public class ActiveSubscriptionAlreadyExistsException extends RuntimeException{

	public ActiveSubscriptionAlreadyExistsException(UUID userId) {
		super("User " + userId + " already has an active subscription");
	}
	
	

}
