package com.hostel.management.dto.request;
import jakarta.validation.constraints.*;
public class StudentRequest {
    @NotBlank @Size(min=3,max=50) private String username;
    @NotBlank @Size(min=6)        private String password;
    @NotBlank @Email               private String email;
    @NotBlank                      private String name;
    @Pattern(regexp="^[0-9]{10}$", message="Phone must be 10 digits") private String phone;
    private String course;
    @Min(1) @Max(6) private Integer year;
    private Long roomId;
    public StudentRequest() {}
    public String getUsername() { return username; } public void setUsername(String v) { this.username = v; }
    public String getPassword() { return password; } public void setPassword(String v) { this.password = v; }
    public String getEmail()    { return email; }    public void setEmail(String v)    { this.email = v; }
    public String getName()     { return name; }     public void setName(String v)     { this.name = v; }
    public String getPhone()    { return phone; }    public void setPhone(String v)    { this.phone = v; }
    public String getCourse()   { return course; }   public void setCourse(String v)   { this.course = v; }
    public Integer getYear()    { return year; }     public void setYear(Integer v)    { this.year = v; }
    public Long getRoomId()     { return roomId; }   public void setRoomId(Long v)     { this.roomId = v; }
}
