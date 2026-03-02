package com.virtusa.telecom.notification.notification_service.service;

import com.virtusa.telecom.notification.notification_service.dto.NotificationRequest;

public interface NotificationService {
    void send(NotificationRequest request);
}
