package com.cb.notification.beans.dtos;

import com.cb.notification.beans.NotificationTemplate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NotificationTemplateResponse {
    private List<NotificationTemplate> notificationTemplates;
    private String status;
    private String statusDescription;
}
