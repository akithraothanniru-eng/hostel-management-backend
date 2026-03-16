package com.hostel.management.service;
import com.hostel.management.dto.request.StudentRequest;
import com.hostel.management.dto.response.StudentResponse;
import java.util.List;
public interface StudentService {
    StudentResponse createStudent(StudentRequest request);
    StudentResponse updateStudent(Long studentId, StudentRequest request);
    void deleteStudent(Long studentId);
    StudentResponse getStudentById(Long studentId);
    StudentResponse getStudentByUserId(Long userId);
    List<StudentResponse> getAllStudents();
    StudentResponse assignRoom(Long studentId, Long roomId);
}
