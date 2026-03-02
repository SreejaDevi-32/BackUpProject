package com.virtusa.telecom.notification.notification_service.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationFactory {

    private final Map<String, NotificationService> services;

    public NotificationService getService(String channel) {
        return services.get(channel.toUpperCase());
    }
}
