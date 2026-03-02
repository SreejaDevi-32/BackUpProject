package com.virtusa.telecom.subscription.subscription_service.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.virtusa.telecom.subscription.subscription_service.systemconstants.SubscriptionStatus;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({
    "subscriptionId",
    "planName",
    "status",
    "startDate",
    "endDate"
})
public class SubscribeResponse {
	
	@JsonProperty("subscriptionId")
	private Long id;
	
	private String planName;
	
	@JsonProperty("status")
	private SubscriptionStatus status;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

}
