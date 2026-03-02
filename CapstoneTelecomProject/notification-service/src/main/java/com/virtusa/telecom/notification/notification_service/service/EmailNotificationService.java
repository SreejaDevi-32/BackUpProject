package com.virtusa.telecom.notification.notification_service.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.virtusa.telecom.notification.notification_service.dto.NotificationRequest;

import lombok.RequiredArgsConstructor;


@Service("EMAIL")
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    @Override
    @Async("notificationExecutor")
    public void send(NotificationRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getRecipient());
            message.setSubject(request.getSubject());
            message.setText(request.getMessage());

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email sending failed");
        }
    }
}