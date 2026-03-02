package com.virtusa.telecom.notification.notification_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentFailedEvent {
	
	private UUID paymentId;

    private UUID subscriptionId;

    private UUID userId;

    private String email;
    private String failureReason;
    
    private String sagaId;

    private LocalDateTime processedAt;
}