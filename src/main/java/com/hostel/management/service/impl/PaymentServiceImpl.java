package com.hostel.management.service.impl;

import com.hostel.management.dto.request.PaymentRequest;
import com.hostel.management.dto.response.PaymentResponse;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.Payment;
import com.hostel.management.model.Student;
import com.hostel.management.repository.PaymentRepository;
import com.hostel.management.repository.StudentRepository;
import com.hostel.management.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", request.getStudentId()));

        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setAmount(request.getAmount());
        payment.setMonth(request.getMonth());
        payment.setStatus(request.getStatus());
        paymentRepository.save(payment);
        return toResponse(payment);
    }

    @Override
    @Transactional
    public PaymentResponse updatePaymentStatus(Long paymentId, Payment.Status status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", paymentId));
        payment.setStatus(status);
        paymentRepository.save(payment);
        return toResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", "id", paymentId));
        return toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponse> getPaymentsByStudent(Long studentId) {
        return paymentRepository.findByStudentStudentId(studentId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponse> getPaymentsByStatus(Payment.Status status) {
        return paymentRepository.findByStatus(status)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    private PaymentResponse toResponse(Payment p) {
        PaymentResponse r = new PaymentResponse();
        r.setPaymentId(p.getPaymentId());
        r.setStudentId(p.getStudent().getStudentId());
        r.setStudentName(p.getStudent().getName());
        r.setAmount(p.getAmount());
        r.setMonth(p.getMonth());
        r.setStatus(p.getStatus());
        return r;
    }
}
