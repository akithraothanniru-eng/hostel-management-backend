package com.hostel.management.service;
import com.hostel.management.dto.request.FoodMenuRequest;
import com.hostel.management.dto.response.FoodMenuResponse;
import com.hostel.management.model.FoodMenu;
import java.util.List;
public interface FoodMenuService {
    FoodMenuResponse createOrUpdateMenu(FoodMenuRequest request);
    FoodMenuResponse getMenuById(Long menuId);
    FoodMenuResponse getMenuByDay(FoodMenu.DayOfWeek day);
    List<FoodMenuResponse> getAllMenus();
}
