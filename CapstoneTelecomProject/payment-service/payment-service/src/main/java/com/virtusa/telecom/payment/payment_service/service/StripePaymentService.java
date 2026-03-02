package com.virtusa.telecom.payment.payment_service.service;

import java.math.BigDecimal;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.virtusa.telecom.payment.payment_service.entity.Payment;


public interface StripePaymentService {
    PaymentIntent createPaymentIntent(
            BigDecimal amount,
            String currency,
            String subscriptionId,
            String userId,
            String sagaId
    ) throws StripeException;
    
    public void cancelPaymentIntent(Payment payment) throws StripeException;
}
