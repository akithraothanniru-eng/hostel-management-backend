package com.hostel.management.dto.request;
import jakarta.validation.constraints.NotBlank;
public class ComplaintRequest {
    @NotBlank private String title;
    private String description;
    public ComplaintRequest() {}
    public String getTitle()       { return title; }       public void setTitle(String v)       { this.title = v; }
    public String getDescription() { return description; } public void setDescription(String v) { this.description = v; }
}
