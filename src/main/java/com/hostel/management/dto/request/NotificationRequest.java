package com.hostel.management.dto.request;
import jakarta.validation.constraints.NotBlank;
public class NotificationRequest {
    @NotBlank private String title;
    @NotBlank private String message;
    public NotificationRequest() {}
    public String getTitle()   { return title; }   public void setTitle(String v)   { this.title = v; }
    public String getMessage() { return message; } public void setMessage(String v) { this.message = v; }
}
