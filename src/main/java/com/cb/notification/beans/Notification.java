package com.cb.notification.beans;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Notification {
    @Id
    private String id;
    private String notificationContent;
    private String emailSubject;
    private List<String> customerIds;
}
