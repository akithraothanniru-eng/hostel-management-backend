package com.hostel.management.service.impl;

import com.hostel.management.dto.request.NotificationRequest;
import com.hostel.management.dto.response.NotificationResponse;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.Notification;
import com.hostel.management.model.User;
import com.hostel.management.repository.NotificationRepository;
import com.hostel.management.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public NotificationResponse sendNotification(NotificationRequest request, User.Role senderRole) {
        Notification notification = new Notification();
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setSenderRole(senderRole);
        notificationRepository.save(notification);
        return toResponse(notification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "id", notificationId));
        notificationRepository.delete(notification);
    }

    private NotificationResponse toResponse(Notification n) {
        NotificationResponse r = new NotificationResponse();
        r.setNotificationId(n.getNotificationId());
        r.setTitle(n.getTitle());
        r.setMessage(n.getMessage());
        r.setSenderRole(n.getSenderRole());
        r.setCreatedAt(n.getCreatedAt());
        return r;
    }
}
