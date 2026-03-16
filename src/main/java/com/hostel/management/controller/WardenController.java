package com.hostel.management.controller;

import com.hostel.management.dto.request.NotificationRequest;
import com.hostel.management.dto.response.*;
import com.hostel.management.model.Complaint;
import com.hostel.management.model.User;
import com.hostel.management.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warden")
@PreAuthorize("hasAnyRole('ADMIN','WARDEN')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WardenController {

    private final RoomService roomService;
    private final ComplaintService complaintService;
    private final NotificationService notificationService;
    private final PaymentService paymentService;

    public WardenController(RoomService roomService, ComplaintService complaintService,
                             NotificationService notificationService, PaymentService paymentService) {
        this.roomService = roomService;
        this.complaintService = complaintService;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
    }

    // ===================== ROOMS =====================

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

    // ===================== COMPLAINTS =====================

    @GetMapping("/complaints")
    public ResponseEntity<ApiResponse<List<ComplaintResponse>>> getAllComplaints() {
        return ResponseEntity.ok(ApiResponse.success("Complaints fetched", complaintService.getAllComplaints()));
    }

    @GetMapping("/complaints/{id}")
    public ResponseEntity<ApiResponse<ComplaintResponse>> getComplaint(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Complaint fetched", complaintService.getComplaintById(id)));
    }

    @PatchMapping("/complaints/{id}/status")
    public ResponseEntity<ApiResponse<ComplaintResponse>> updateComplaintStatus(
            @PathVariable Long id, @RequestParam Complaint.Status status) {
        return ResponseEntity.ok(ApiResponse.success("Status updated",
                complaintService.updateComplaintStatus(id, status)));
    }

    // ===================== NOTIFICATIONS =====================

    @PostMapping("/notifications")
    public ResponseEntity<ApiResponse<NotificationResponse>> sendNotification(
            @Valid @RequestBody NotificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Notification sent",
                        notificationService.sendNotification(request, User.Role.WARDEN)));
    }

    @GetMapping("/notifications")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getAllNotifications() {
        return ResponseEntity.ok(ApiResponse.success("Notifications fetched", notificationService.getAllNotifications()));
    }

    // ===================== PAYMENTS =====================

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {
        return ResponseEntity.ok(ApiResponse.success("Payments fetched", paymentService.getAllPayments()));
    }

    @GetMapping("/payments/student/{studentId}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getPaymentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(ApiResponse.success("Payments fetched",
                paymentService.getPaymentsByStudent(studentId)));
    }
}
