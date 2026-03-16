package com.hostel.management.dto.response;
import com.hostel.management.model.User;
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String email;
    private User.Role role;
    public JwtResponse() {}
    public JwtResponse(String token, Long userId, String username, String email, User.Role role) {
        this.token=token; this.userId=userId; this.username=username; this.email=email; this.role=role;
    }
    public String getToken()    { return token; }    public void setToken(String v)    { this.token = v; }
    public String getType()     { return type; }     public void setType(String v)     { this.type = v; }
    public Long getUserId()     { return userId; }   public void setUserId(Long v)     { this.userId = v; }
    public String getUsername() { return username; } public void setUsername(String v) { this.username = v; }
    public String getEmail()    { return email; }    public void setEmail(String v)    { this.email = v; }
    public User.Role getRole()  { return role; }     public void setRole(User.Role v)  { this.role = v; }
}
