package com.virtusa.telecom.notification.notification_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.telecom.notification.notification_service.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatus(String status);

    List<Notification> findByReferenceId(String referenceId);
}