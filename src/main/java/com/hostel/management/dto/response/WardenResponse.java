package com.hostel.management.dto.response;
public class WardenResponse {
    private Long wardenId; private Long userId; private String username; private String email;
    public WardenResponse() {}
    public WardenResponse(Long wardenId, Long userId, String username, String email) {
        this.wardenId=wardenId; this.userId=userId; this.username=username; this.email=email;
    }
    public Long getWardenId()   { return wardenId; }  public void setWardenId(Long v)   { this.wardenId = v; }
    public Long getUserId()     { return userId; }    public void setUserId(Long v)     { this.userId = v; }
    public String getUsername() { return username; }  public void setUsername(String v) { this.username = v; }
    public String getEmail()    { return email; }     public void setEmail(String v)    { this.email = v; }
}
