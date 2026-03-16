package com.hostel.management.service;
import com.hostel.management.dto.request.ComplaintRequest;
import com.hostel.management.dto.response.ComplaintResponse;
import com.hostel.management.model.Complaint;
import java.util.List;
public interface ComplaintService {
    ComplaintResponse raiseComplaint(Long studentId, ComplaintRequest request);
    ComplaintResponse updateComplaintStatus(Long complaintId, Complaint.Status status);
    ComplaintResponse getComplaintById(Long complaintId);
    List<ComplaintResponse> getAllComplaints();
    List<ComplaintResponse> getComplaintsByStudent(Long studentId);
    List<ComplaintResponse> getComplaintsByStatus(Complaint.Status status);
    void deleteComplaint(Long complaintId);
}
