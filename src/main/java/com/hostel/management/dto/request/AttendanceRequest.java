package com.hostel.management.dto.request;
import com.hostel.management.model.Attendance;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
public class AttendanceRequest {
    @NotNull private LocalDate date;
    @NotNull private Attendance.Status status;
    public AttendanceRequest() {}
    public LocalDate getDate()           { return date; }   public void setDate(LocalDate v)           { this.date = v; }
    public Attendance.Status getStatus() { return status; } public void setStatus(Attendance.Status v) { this.status = v; }
}
