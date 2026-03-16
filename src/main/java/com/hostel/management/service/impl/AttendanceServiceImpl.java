package com.hostel.management.service.impl;

import com.hostel.management.dto.request.AttendanceRequest;
import com.hostel.management.dto.response.AttendanceResponse;
import com.hostel.management.exception.BadRequestException;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.Attendance;
import com.hostel.management.model.Student;
import com.hostel.management.repository.AttendanceRepository;
import com.hostel.management.repository.StudentRepository;
import com.hostel.management.service.AttendanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public AttendanceResponse markAttendance(Long studentId, AttendanceRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        Optional<Attendance> existing = attendanceRepository
                .findByStudentStudentIdAndDate(studentId, request.getDate());
        if (existing.isPresent())
            throw new BadRequestException("Attendance already marked for date: " + request.getDate());

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setDate(request.getDate());
        attendance.setStatus(request.getStatus());
        attendanceRepository.save(attendance);
        return toResponse(attendance);
    }

    @Override
    @Transactional
    public AttendanceResponse updateAttendance(Long attendanceId, AttendanceRequest request) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance", "id", attendanceId));
        attendance.setDate(request.getDate());
        attendance.setStatus(request.getStatus());
        attendanceRepository.save(attendance);
        return toResponse(attendance);
    }

    @Override
    public List<AttendanceResponse> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentStudentId(studentId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponse> getAttendanceByStudentAndDateRange(Long studentId, LocalDate from, LocalDate to) {
        return attendanceRepository.findByStudentStudentIdAndDateBetween(studentId, from, to)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponse> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponse> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AttendanceResponse toResponse(Attendance a) {
        AttendanceResponse r = new AttendanceResponse();
        r.setAttendanceId(a.getAttendanceId());
        r.setStudentId(a.getStudent().getStudentId());
        r.setStudentName(a.getStudent().getName());
        r.setDate(a.getDate());
        r.setStatus(a.getStatus());
        return r;
    }
}
