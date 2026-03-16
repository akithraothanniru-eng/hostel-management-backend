package com.hostel.management.dto.response;
public class StudentResponse {
    private Long studentId; private Long userId; private String username; private String email;
    private String name; private String phone; private String course; private Integer year;
    private Long roomId; private String roomNumber;
    public StudentResponse() {}
    public Long getStudentId()    { return studentId; }   public void setStudentId(Long v)    { this.studentId = v; }
    public Long getUserId()       { return userId; }      public void setUserId(Long v)       { this.userId = v; }
    public String getUsername()   { return username; }    public void setUsername(String v)   { this.username = v; }
    public String getEmail()      { return email; }       public void setEmail(String v)      { this.email = v; }
    public String getName()       { return name; }        public void setName(String v)       { this.name = v; }
    public String getPhone()      { return phone; }       public void setPhone(String v)      { this.phone = v; }
    public String getCourse()     { return course; }      public void setCourse(String v)     { this.course = v; }
    public Integer getYear()      { return year; }        public void setYear(Integer v)      { this.year = v; }
    public Long getRoomId()       { return roomId; }      public void setRoomId(Long v)       { this.roomId = v; }
    public String getRoomNumber() { return roomNumber; }  public void setRoomNumber(String v) { this.roomNumber = v; }
}
