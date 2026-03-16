package com.hostel.management.dto.response;
import com.hostel.management.model.User;
import java.time.LocalDateTime;
public class UserResponse {
    private Long userId; private String username; private String email;
    private User.Role role; private LocalDateTime createdAt;
    public UserResponse() {}
    public UserResponse(Long userId, String username, String email, User.Role role, LocalDateTime createdAt) {
        this.userId=userId; this.username=username; this.email=email; this.role=role; this.createdAt=createdAt;
    }
    public Long getUserId()           { return userId; }    public void setUserId(Long v)           { this.userId = v; }
    public String getUsername()       { return username; }  public void setUsername(String v)       { this.username = v; }
    public String getEmail()          { return email; }     public void setEmail(String v)          { this.email = v; }
    public User.Role getRole()        { return role; }      public void setRole(User.Role v)        { this.role = v; }
    public LocalDateTime getCreatedAt(){ return createdAt;} public void setCreatedAt(LocalDateTime v){ this.createdAt = v; }
}
