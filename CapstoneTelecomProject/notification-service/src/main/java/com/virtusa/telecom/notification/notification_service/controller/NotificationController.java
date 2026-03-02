package com.virtusa.telecom.notification.notification_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.telecom.notification.notification_service.dto.NotificationRequest;
import com.virtusa.telecom.notification.notification_service.service.NotificationManager;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationManager manager;

    @PostMapping
    public ResponseEntity<String> send(
            @RequestBody NotificationRequest request) {

        manager.sendNotification(request);
        return ResponseEntity.ok("Notification triggered");
    }
}