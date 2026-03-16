package com.hostel.management.repository;
import com.hostel.management.model.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {
    Optional<FoodMenu> findByDayOfWeek(FoodMenu.DayOfWeek dayOfWeek);
}
