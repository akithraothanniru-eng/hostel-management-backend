package com.hostel.management.repository;
import com.hostel.management.model.Notification;
import com.hostel.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findBySenderRole(User.Role senderRole);
    List<Notification> findAllByOrderByCreatedAtDesc();
}
