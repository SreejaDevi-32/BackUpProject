package com.virtusa.telecom.payment.payment_service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GatewayResponse {
    private boolean success;
    private String transactionId;
    private String failureReason;
}
