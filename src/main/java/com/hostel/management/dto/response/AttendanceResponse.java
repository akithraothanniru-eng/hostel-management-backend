package com.hostel.management.dto.response;
import com.hostel.management.model.Attendance;
import java.time.LocalDate;
public class AttendanceResponse {
    private Long attendanceId; private Long studentId; private String studentName;
    private LocalDate date; private Attendance.Status status;
    public AttendanceResponse() {}
    public Long getAttendanceId()        { return attendanceId; } public void setAttendanceId(Long v)        { this.attendanceId = v; }
    public Long getStudentId()           { return studentId; }    public void setStudentId(Long v)           { this.studentId = v; }
    public String getStudentName()       { return studentName; }  public void setStudentName(String v)       { this.studentName = v; }
    public LocalDate getDate()           { return date; }         public void setDate(LocalDate v)           { this.date = v; }
    public Attendance.Status getStatus() { return status; }       public void setStatus(Attendance.Status v) { this.status = v; }
}
