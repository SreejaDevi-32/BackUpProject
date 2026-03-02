package com.virtusa.telecom.payment.payment_service.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.virtusa.telecom.payment.payment_service.dto.GatewayRequest;
import com.virtusa.telecom.payment.payment_service.dto.GatewayResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StripePaymentGateway implements PaymentGateway {

    private final StripePaymentService stripePaymentService;

    @Override
    public GatewayResponse charge(GatewayRequest request) {

        try {
            PaymentIntent intent = stripePaymentService.createPaymentIntent(
                    request.getAmount(),
                    request.getCurrency(),
                    request.getSubscriptionId().toString(),
                    request.getUserId().toString(),
                    request.getSagaId()
            );

            return GatewayResponse.builder()
                    .success(true)
                    .transactionId(intent.getId())
                    .build();

        } catch (Exception ex) {
            return GatewayResponse.builder()
                    .success(false)
                    .failureReason(ex.getMessage())
                    .build();
        }
    }
}