package com.hostel.management.dto.response;
import com.hostel.management.model.User;
import java.time.LocalDateTime;
public class NotificationResponse {
    private Long notificationId; private String title; private String message;
    private User.Role senderRole; private LocalDateTime createdAt;
    public NotificationResponse() {}
    public Long getNotificationId()  { return notificationId; } public void setNotificationId(Long v)  { this.notificationId = v; }
    public String getTitle()         { return title; }          public void setTitle(String v)         { this.title = v; }
    public String getMessage()       { return message; }        public void setMessage(String v)       { this.message = v; }
    public User.Role getSenderRole() { return senderRole; }     public void setSenderRole(User.Role v) { this.senderRole = v; }
    public LocalDateTime getCreatedAt(){ return createdAt; }    public void setCreatedAt(LocalDateTime v){ this.createdAt = v; }
}
