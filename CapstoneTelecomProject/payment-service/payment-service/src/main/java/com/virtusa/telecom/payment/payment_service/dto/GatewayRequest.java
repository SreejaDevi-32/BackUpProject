package com.virtusa.telecom.payment.payment_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GatewayRequest {

    private UUID subscriptionId;
    private UUID userId;
    private BigDecimal amount;
    private String currency;
    private String sagaId;
}