package com.hostel.management.service;
import com.hostel.management.dto.request.NotificationRequest;
import com.hostel.management.dto.response.NotificationResponse;
import com.hostel.management.model.User;
import java.util.List;
public interface NotificationService {
    NotificationResponse sendNotification(NotificationRequest request, User.Role senderRole);
    List<NotificationResponse> getAllNotifications();
    void deleteNotification(Long notificationId);
}
