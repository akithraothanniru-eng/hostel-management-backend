package com.hostel.management.service;
import com.hostel.management.dto.request.PaymentRequest;
import com.hostel.management.dto.response.PaymentResponse;
import com.hostel.management.model.Payment;
import java.util.List;
public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
    PaymentResponse updatePaymentStatus(Long paymentId, Payment.Status status);
    PaymentResponse getPaymentById(Long paymentId);
    List<PaymentResponse> getAllPayments();
    List<PaymentResponse> getPaymentsByStudent(Long studentId);
    List<PaymentResponse> getPaymentsByStatus(Payment.Status status);
}
