package com.hostel.management.service.impl;

import com.hostel.management.dto.request.ComplaintRequest;
import com.hostel.management.dto.response.ComplaintResponse;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.Complaint;
import com.hostel.management.model.Student;
import com.hostel.management.repository.ComplaintRepository;
import com.hostel.management.repository.StudentRepository;
import com.hostel.management.service.ComplaintService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final StudentRepository studentRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository, StudentRepository studentRepository) {
        this.complaintRepository = complaintRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public ComplaintResponse raiseComplaint(Long studentId, ComplaintRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        Complaint complaint = new Complaint();
        complaint.setStudent(student);
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setStatus(Complaint.Status.PENDING);
        complaintRepository.save(complaint);
        return toResponse(complaint);
    }

    @Override
    @Transactional
    public ComplaintResponse updateComplaintStatus(Long complaintId, Complaint.Status status) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", complaintId));
        complaint.setStatus(status);
        complaintRepository.save(complaint);
        return toResponse(complaint);
    }

    @Override
    public ComplaintResponse getComplaintById(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", complaintId));
        return toResponse(complaint);
    }

    @Override
    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ComplaintResponse> getComplaintsByStudent(Long studentId) {
        return complaintRepository.findByStudentStudentId(studentId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ComplaintResponse> getComplaintsByStatus(Complaint.Status status) {
        return complaintRepository.findByStatus(status)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteComplaint(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint", "id", complaintId));
        complaintRepository.delete(complaint);
    }

    private ComplaintResponse toResponse(Complaint c) {
        ComplaintResponse r = new ComplaintResponse();
        r.setComplaintId(c.getComplaintId());
        r.setStudentId(c.getStudent().getStudentId());
        r.setStudentName(c.getStudent().getName());
        r.setTitle(c.getTitle());
        r.setDescription(c.getDescription());
        r.setStatus(c.getStatus());
        r.setCreatedAt(c.getCreatedAt());
        return r;
    }
}