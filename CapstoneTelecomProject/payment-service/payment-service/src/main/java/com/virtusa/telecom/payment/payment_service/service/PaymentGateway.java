package com.virtusa.telecom.payment.payment_service.service;

import java.math.BigDecimal;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.virtusa.telecom.payment.payment_service.dto.GatewayRequest;
import com.virtusa.telecom.payment.payment_service.dto.GatewayResponse;

public interface PaymentGateway {
	public GatewayResponse charge(GatewayRequest request);
}
