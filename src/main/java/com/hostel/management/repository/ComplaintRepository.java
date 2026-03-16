package com.hostel.management.repository;
import com.hostel.management.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStudentStudentId(Long studentId);
    List<Complaint> findByStatus(Complaint.Status status);
}
