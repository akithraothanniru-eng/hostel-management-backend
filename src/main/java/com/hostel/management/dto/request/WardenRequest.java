package com.hostel.management.dto.request;
import jakarta.validation.constraints.*;
public class WardenRequest {
    @NotBlank @Size(min=3,max=50) private String username;
    @NotBlank @Size(min=6)        private String password;
    @NotBlank @Email               private String email;
    public WardenRequest() {}
    public String getUsername() { return username; } public void setUsername(String v) { this.username = v; }
    public String getPassword() { return password; } public void setPassword(String v) { this.password = v; }
    public String getEmail()    { return email; }    public void setEmail(String v)    { this.email = v; }
}
