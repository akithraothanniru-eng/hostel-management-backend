package com.hostel.management.service;
import com.hostel.management.dto.request.AttendanceRequest;
import com.hostel.management.dto.response.AttendanceResponse;
import java.time.LocalDate;
import java.util.List;
public interface AttendanceService {
    AttendanceResponse markAttendance(Long studentId, AttendanceRequest request);
    AttendanceResponse updateAttendance(Long attendanceId, AttendanceRequest request);
    List<AttendanceResponse> getAttendanceByStudent(Long studentId);
    List<AttendanceResponse> getAttendanceByStudentAndDateRange(Long studentId, LocalDate from, LocalDate to);
    List<AttendanceResponse> getAttendanceByDate(LocalDate date);
    List<AttendanceResponse> getAllAttendance();
}
