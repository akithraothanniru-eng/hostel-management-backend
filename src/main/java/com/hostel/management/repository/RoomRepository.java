package com.hostel.management.repository;
import com.hostel.management.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomNumber);
    boolean existsByRoomNumber(String roomNumber);
    List<Room> findByWardenWardenId(Long wardenId);
    @Query("SELECT r FROM Room r WHERE r.capacity > (SELECT COUNT(s) FROM Student s WHERE s.room = r)")
    List<Room> findAvailableRooms();
}
