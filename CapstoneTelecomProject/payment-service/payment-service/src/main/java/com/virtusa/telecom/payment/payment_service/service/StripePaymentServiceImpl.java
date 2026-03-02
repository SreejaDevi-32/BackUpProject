package com.virtusa.telecom.payment.payment_service.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import com.virtusa.telecom.payment.payment_service.entity.Payment;

import jakarta.annotation.PostConstruct;

@Service
public class StripePaymentServiceImpl implements StripePaymentService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Override
    public PaymentIntent createPaymentIntent(
            BigDecimal amount,
            String currency,
            String subscriptionId,
            String userId,
            String sagaId
    ) throws StripeException {

        long stripeAmount = amount
                .multiply(BigDecimal.valueOf(100))
                .longValueExact();

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(stripeAmount)
                        .setCurrency(currency)
                        .addPaymentMethodType("card") 
                        .setPaymentMethod("pm_card_visa")
                        .setConfirm(true)
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(false)   
                                        .build()
                        )
                        .putMetadata("subscriptionId", subscriptionId)
                        .putMetadata("userId", userId)
                        .putMetadata("sagaId", sagaId)
                        .build();
        
        RequestOptions requestOptions = RequestOptions.builder()
        											  .setIdempotencyKey(sagaId)
        											  .build();
        return PaymentIntent.create(params,requestOptions);
    }
    
    @Override
    public void cancelPaymentIntent(Payment payment) throws StripeException {

        PaymentIntent intent =
                PaymentIntent.retrieve(payment.getPaymentIntentId());

        if (!"succeeded".equals(intent.getStatus())
                && !"canceled".equals(intent.getStatus())) {

            intent.cancel();
        }
    }

        //payment.setStatus("canceled");
       // paymentRepository.save(payment);
}