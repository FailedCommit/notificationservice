package com.cb.notification.beans;

import com.cb.notification.beans.enums.NotificationType;
import lombok.Data;

import java.util.List;

@Data
public class NotificationPreference {
    private String customerId;
    private List<NotificationType> preferredNotificationTypes;
}
