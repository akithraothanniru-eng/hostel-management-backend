package com.hostel.management.repository;
import com.hostel.management.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentStudentId(Long studentId);
    List<Attendance> findByStudentStudentIdAndDateBetween(Long studentId, LocalDate from, LocalDate to);
    Optional<Attendance> findByStudentStudentIdAndDate(Long studentId, LocalDate date);
    List<Attendance> findByDate(LocalDate date);
}
