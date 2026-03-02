package com.virtusa.telecom.notification.notification_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    @NotNull(message = "Channel is required")
    private NotificationChannel channel;

    /**
     * EMAIL → email address
     * SMS   → phone number
     */
    @NotBlank(message = "Recipient is required")
    private String recipient;

    /**
     * Required only for EMAIL
     */
    private String subject;

    @NotBlank(message = "Message cannot be empty")
    private String message;

    /**
     * Optional template name
     */
    private String templateName;

    /**
     * Optional metadata (future usage)
     */
    private String referenceId;
    
    
    public boolean isEmail() {
        return channel == NotificationChannel.EMAIL;
    }

    public boolean isSms() {
        return channel == NotificationChannel.SMS;
    }
}
