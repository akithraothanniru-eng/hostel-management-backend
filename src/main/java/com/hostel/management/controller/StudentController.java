package com.hostel.management.controller;

import com.hostel.management.dto.request.AttendanceRequest;
import com.hostel.management.dto.request.ComplaintRequest;
import com.hostel.management.dto.response.*;
import com.hostel.management.model.FoodMenu;
import com.hostel.management.security.UserDetailsImpl;
import com.hostel.management.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@PreAuthorize("hasAnyRole('ADMIN','WARDEN','STUDENT')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentController {

    private final StudentService studentService;
    private final ComplaintService complaintService;
    private final FoodMenuService foodMenuService;
    private final NotificationService notificationService;
    private final PaymentService paymentService;
    private final AttendanceService attendanceService;

    public StudentController(StudentService studentService, ComplaintService complaintService,
                              FoodMenuService foodMenuService, NotificationService notificationService,
                              PaymentService paymentService, AttendanceService attendanceService) {
        this.studentService = studentService;
        this.complaintService = complaintService;
        this.foodMenuService = foodMenuService;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
        this.attendanceService = attendanceService;
    }

    // ===================== PROFILE =====================

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<StudentResponse>> getMyProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(ApiResponse.success("Profile fetched",
                studentService.getStudentByUserId(userDetails.getUserId())));
    }

    // ===================== COMPLAINTS =====================

    @PostMapping("/complaints")
    public ResponseEntity<ApiResponse<ComplaintResponse>> raiseComplaint(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody ComplaintRequest request) {
        StudentResponse student = studentService.getStudentByUserId(userDetails.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Complaint raised",
                        complaintService.raiseComplaint(student.getStudentId(), request)));
    }

    @GetMapping("/complaints")
    public ResponseEntity<ApiResponse<List<ComplaintResponse>>> getMyComplaints(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        StudentResponse student = studentService.getStudentByUserId(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Complaints fetched",
                complaintService.getComplaintsByStudent(student.getStudentId())));
    }

    @GetMapping("/complaints/{id}")
    public ResponseEntity<ApiResponse<ComplaintResponse>> getComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Complaint fetched", complaintService.getComplaintById(id)));
    }

    // ===================== FOOD MENU =====================

    @GetMapping("/food-menu")
    public ResponseEntity<ApiResponse<List<FoodMenuResponse>>> getAllMenus() {
        return ResponseEntity.ok(ApiResponse.success("Menu fetched", foodMenuService.getAllMenus()));
    }

    @GetMapping("/food-menu/{day}")
    public ResponseEntity<ApiResponse<FoodMenuResponse>> getMenuByDay(@PathVariable FoodMenu.DayOfWeek day) {
        return ResponseEntity.ok(ApiResponse.success("Menu fetched", foodMenuService.getMenuByDay(day)));
    }

    // ===================== NOTIFICATIONS =====================

    @GetMapping("/notifications")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getAllNotifications() {
        return ResponseEntity.ok(ApiResponse.success("Notifications fetched", notificationService.getAllNotifications()));
    }

    // ===================== PAYMENTS =====================

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getMyPayments(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        StudentResponse student = studentService.getStudentByUserId(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Payments fetched",
                paymentService.getPaymentsByStudent(student.getStudentId())));
    }

    // ===================== ATTENDANCE =====================

    @PostMapping("/attendance")
    public ResponseEntity<ApiResponse<AttendanceResponse>> markAttendance(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody AttendanceRequest request) {
        StudentResponse student = studentService.getStudentByUserId(userDetails.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Attendance marked",
                        attendanceService.markAttendance(student.getStudentId(), request)));
    }

    @PutMapping("/attendance/{id}")
    public ResponseEntity<ApiResponse<AttendanceResponse>> updateAttendance(
            @PathVariable Long id, @Valid @RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Attendance updated",
                attendanceService.updateAttendance(id, request)));
    }

    @GetMapping("/attendance")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getMyAttendance(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        StudentResponse student = studentService.getStudentByUserId(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Attendance fetched",
                attendanceService.getAttendanceByStudent(student.getStudentId())));
    }

    @GetMapping("/attendance/range")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getMyAttendanceByRange(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam String from,
            @RequestParam String to) {
        StudentResponse student = studentService.getStudentByUserId(userDetails.getUserId());
        return ResponseEntity.ok(ApiResponse.success("Attendance fetched",
                attendanceService.getAttendanceByStudentAndDateRange(
                        student.getStudentId(),
                        java.time.LocalDate.parse(from),
                        java.time.LocalDate.parse(to))));
    }
}
