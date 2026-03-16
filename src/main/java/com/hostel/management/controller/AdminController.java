package com.hostel.management.controller;

import com.hostel.management.dto.request.*;
import com.hostel.management.dto.response.*;
import com.hostel.management.model.Complaint;
import com.hostel.management.model.Payment;
import com.hostel.management.model.User;
import com.hostel.management.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    private final StudentService studentService;
    private final WardenService wardenService;
    private final RoomService roomService;
    private final ComplaintService complaintService;
    private final FoodMenuService foodMenuService;
    private final NotificationService notificationService;
    private final PaymentService paymentService;
    private final AttendanceService attendanceService;

    public AdminController(StudentService studentService, WardenService wardenService,
                           RoomService roomService, ComplaintService complaintService,
                           FoodMenuService foodMenuService, NotificationService notificationService,
                           PaymentService paymentService, AttendanceService attendanceService) {
        this.studentService = studentService;
        this.wardenService = wardenService;
        this.roomService = roomService;
        this.complaintService = complaintService;
        this.foodMenuService = foodMenuService;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
        this.attendanceService = attendanceService;
    }

    // ===================== STUDENTS =====================

    @PostMapping("/students")
    public ResponseEntity<ApiResponse<StudentResponse>> createStudent(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Student created", studentService.createStudent(request)));
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents() {
        return ResponseEntity.ok(ApiResponse.success("Students fetched", studentService.getAllStudents()));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Student fetched", studentService.getStudentById(id)));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(
            @PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Student updated", studentService.updateStudent(id, request)));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.success("Student deleted"));
    }

    @PatchMapping("/students/{id}/assign-room/{roomId}")
    public ResponseEntity<ApiResponse<StudentResponse>> assignRoom(
            @PathVariable Long id, @PathVariable Long roomId) {
        return ResponseEntity.ok(ApiResponse.success("Room assigned", studentService.assignRoom(id, roomId)));
    }

    // ===================== WARDENS =====================

    @PostMapping("/wardens")
    public ResponseEntity<ApiResponse<WardenResponse>> createWarden(@Valid @RequestBody WardenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Warden created", wardenService.createWarden(request)));
    }

    @GetMapping("/wardens")
    public ResponseEntity<ApiResponse<List<WardenResponse>>> getAllWardens() {
        return ResponseEntity.ok(ApiResponse.success("Wardens fetched", wardenService.getAllWardens()));
    }

    @GetMapping("/wardens/{id}")
    public ResponseEntity<ApiResponse<WardenResponse>> getWarden(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Warden fetched", wardenService.getWardenById(id)));
    }

    @PutMapping("/wardens/{id}")
    public ResponseEntity<ApiResponse<WardenResponse>> updateWarden(
            @PathVariable Long id, @Valid @RequestBody WardenRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Warden updated", wardenService.updateWarden(id, request)));
    }

    @DeleteMapping("/wardens/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteWarden(@PathVariable Long id) {
        wardenService.deleteWarden(id);
        return ResponseEntity.ok(ApiResponse.success("Warden deleted"));
    }

    // ===================== ROOMS =====================

    @PostMapping("/rooms")
    public ResponseEntity<ApiResponse<RoomResponse>> createRoom(@Valid @RequestBody RoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Room created", roomService.createRoom(request)));
    }

    @GetMapping("/rooms")
    public ResponseEntity<ApiResponse<List<RoomResponse>>> getAllRooms() {
        return ResponseEntity.ok(ApiResponse.success("Rooms fetched", roomService.getAllRooms()));
    }

    @GetMapping("/rooms/available")
    public ResponseEntity<ApiResponse<List<RoomResponse>>> getAvailableRooms() {
        return ResponseEntity.ok(ApiResponse.success("Available rooms fetched", roomService.getAvailableRooms()));
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<ApiResponse<RoomResponse>> getRoom(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Room fetched", roomService.getRoomById(id)));
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<ApiResponse<RoomResponse>> updateRoom(
            @PathVariable Long id, @Valid @RequestBody RoomRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Room updated", roomService.updateRoom(id, request)));
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(ApiResponse.success("Room deleted"));
    }

    // ===================== COMPLAINTS =====================

    @GetMapping("/complaints")
    public ResponseEntity<ApiResponse<List<ComplaintResponse>>> getAllComplaints() {
        return ResponseEntity.ok(ApiResponse.success("Complaints fetched", complaintService.getAllComplaints()));
    }

    @GetMapping("/complaints/{id}")
    public ResponseEntity<ApiResponse<ComplaintResponse>> getComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Complaint fetched", complaintService.getComplaintById(id)));
    }

    @GetMapping("/complaints/status/{status}")
    public ResponseEntity<ApiResponse<List<ComplaintResponse>>> getComplaintsByStatus(
            @PathVariable Complaint.Status status) {
        return ResponseEntity.ok(ApiResponse.success("Complaints fetched", complaintService.getComplaintsByStatus(status)));
    }

    @PatchMapping("/complaints/{id}/status")
    public ResponseEntity<ApiResponse<ComplaintResponse>> updateComplaintStatus(
            @PathVariable Long id, @RequestParam Complaint.Status status) {
        return ResponseEntity.ok(ApiResponse.success("Status updated", complaintService.updateComplaintStatus(id, status)));
    }

    @DeleteMapping("/complaints/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.ok(ApiResponse.success("Complaint deleted"));
    }

    // ===================== FOOD MENU =====================

    @PostMapping("/food-menu")
    public ResponseEntity<ApiResponse<FoodMenuResponse>> createOrUpdateMenu(@Valid @RequestBody FoodMenuRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Menu saved", foodMenuService.createOrUpdateMenu(request)));
    }

    @GetMapping("/food-menu")
    public ResponseEntity<ApiResponse<List<FoodMenuResponse>>> getAllMenus() {
        return ResponseEntity.ok(ApiResponse.success("Menus fetched", foodMenuService.getAllMenus()));
    }

    // ===================== NOTIFICATIONS =====================

    @PostMapping("/notifications")
    public ResponseEntity<ApiResponse<NotificationResponse>> sendNotification(
            @Valid @RequestBody NotificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Notification sent",
                        notificationService.sendNotification(request, User.Role.ADMIN)));
    }

    @GetMapping("/notifications")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getAllNotifications() {
        return ResponseEntity.ok(ApiResponse.success("Notifications fetched", notificationService.getAllNotifications()));
    }

    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok(ApiResponse.success("Notification deleted"));
    }

    // ===================== PAYMENTS =====================

    @PostMapping("/payments")
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Payment created", paymentService.createPayment(request)));
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {
        return ResponseEntity.ok(ApiResponse.success("Payments fetched", paymentService.getAllPayments()));
    }

    @GetMapping("/payments/student/{studentId}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getPaymentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(ApiResponse.success("Payments fetched", paymentService.getPaymentsByStudent(studentId)));
    }

    @GetMapping("/payments/status/{status}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getPaymentsByStatus(@PathVariable Payment.Status status) {
        return ResponseEntity.ok(ApiResponse.success("Payments fetched", paymentService.getPaymentsByStatus(status)));
    }

    @PatchMapping("/payments/{id}/status")
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePaymentStatus(
            @PathVariable Long id, @RequestParam Payment.Status status) {
        return ResponseEntity.ok(ApiResponse.success("Payment status updated", paymentService.updatePaymentStatus(id, status)));
    }

    // ===================== ATTENDANCE =====================

    @GetMapping("/attendance")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendance() {
        return ResponseEntity.ok(ApiResponse.success("Attendance fetched", attendanceService.getAllAttendance()));
    }

    @GetMapping("/attendance/student/{studentId}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(ApiResponse.success("Attendance fetched", attendanceService.getAttendanceByStudent(studentId)));
    }

    @GetMapping("/attendance/date/{date}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByDate(
            @PathVariable String date) {
        return ResponseEntity.ok(ApiResponse.success("Attendance fetched",
                attendanceService.getAttendanceByDate(java.time.LocalDate.parse(date))));
    }

    @GetMapping("/attendance/student/{studentId}/range")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByRange(
            @PathVariable Long studentId,
            @RequestParam String from,
            @RequestParam String to) {
        return ResponseEntity.ok(ApiResponse.success("Attendance fetched",
                attendanceService.getAttendanceByStudentAndDateRange(
                        studentId,
                        java.time.LocalDate.parse(from),
                        java.time.LocalDate.parse(to))));
    }
}
