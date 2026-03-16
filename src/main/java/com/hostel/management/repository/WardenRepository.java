package com.hostel.management.repository;
import com.hostel.management.model.Warden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface WardenRepository extends JpaRepository<Warden, Long> {
    Optional<Warden> findByUserUserId(Long userId);
    boolean existsByUserUserId(Long userId);
}
