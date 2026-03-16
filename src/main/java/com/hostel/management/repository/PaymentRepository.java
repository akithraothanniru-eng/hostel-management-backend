package com.hostel.management.repository;
import com.hostel.management.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentStudentId(Long studentId);
    List<Payment> findByStatus(Payment.Status status);
    List<Payment> findByStudentStudentIdAndMonth(Long studentId, String month);
}
