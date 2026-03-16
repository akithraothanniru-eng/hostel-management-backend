package com.hostel.management.dto.response;
import com.hostel.management.model.Complaint;
import java.time.LocalDateTime;
public class ComplaintResponse {
    private Long complaintId; private Long studentId; private String studentName;
    private String title; private String description; private Complaint.Status status; private LocalDateTime createdAt;
    public ComplaintResponse() {}
    public Long getComplaintId()        { return complaintId; }  public void setComplaintId(Long v)        { this.complaintId = v; }
    public Long getStudentId()          { return studentId; }    public void setStudentId(Long v)          { this.studentId = v; }
    public String getStudentName()      { return studentName; }  public void setStudentName(String v)      { this.studentName = v; }
    public String getTitle()            { return title; }        public void setTitle(String v)            { this.title = v; }
    public String getDescription()      { return description; }  public void setDescription(String v)      { this.description = v; }
    public Complaint.Status getStatus() { return status; }       public void setStatus(Complaint.Status v) { this.status = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }    public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
}
