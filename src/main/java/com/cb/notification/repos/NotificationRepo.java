package com.cb.notification.repos;

import com.cb.notification.beans.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepo extends MongoRepository<Notification, String> {
}
