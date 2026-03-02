package com.virtusa.telecom.notification.notification_service.service;

import com.virtusa.telecom.notification.notification_service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PaymentEventHandler {

    private final NotificationManager notificationManager;

    @Bean
    public Consumer<PaymentSuccessEvent> paymentSuccess() {
        return event -> {

            NotificationRequest request = new NotificationRequest();
            request.setRecipient(event.getEmail());
            request.setSubject("Subscription Activated");
            request.setMessage("Your payment was successful. " + event.getMessage());
            request.setReferenceId(event.getPaymentId().toString());
            request.setChannel(NotificationChannel.EMAIL);
            log.info("Payment Consumed By Notification Service for Success case"+request);

            notificationManager.sendNotification(request);
        };
    }

    @Bean
    public Consumer<PaymentFailedEvent> paymentFailed() {
        return event -> {

            NotificationRequest request = new NotificationRequest();
            request.setRecipient(event.getEmail());
            request.setSubject("Payment Failed");
            request.setMessage("Your payment failed. Please try again. " + event.getFailureReason());
            request.setReferenceId(event.getPaymentId().toString());
            request.setChannel(NotificationChannel.EMAIL);
            log.info("Payment Consumed By Notification Service for failure case"+request);

            notificationManager.sendNotification(request);
        };
    }
}