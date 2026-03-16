package com.hostel.management.service.impl;

import com.hostel.management.dto.request.FoodMenuRequest;
import com.hostel.management.dto.response.FoodMenuResponse;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.FoodMenu;
import com.hostel.management.repository.FoodMenuRepository;
import com.hostel.management.service.FoodMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodMenuServiceImpl implements FoodMenuService {

    private final FoodMenuRepository foodMenuRepository;

    public FoodMenuServiceImpl(FoodMenuRepository foodMenuRepository) {
        this.foodMenuRepository = foodMenuRepository;
    }

    @Override
    @Transactional
    public FoodMenuResponse createOrUpdateMenu(FoodMenuRequest request) {
        FoodMenu menu = foodMenuRepository.findByDayOfWeek(request.getDayOfWeek())
                .orElse(new FoodMenu());
        menu.setDayOfWeek(request.getDayOfWeek());
        menu.setBreakfast(request.getBreakfast());
        menu.setLunch(request.getLunch());
        menu.setDinner(request.getDinner());
        foodMenuRepository.save(menu);
        return toResponse(menu);
    }

    @Override
    public FoodMenuResponse getMenuById(Long menuId) {
        FoodMenu menu = foodMenuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("FoodMenu", "id", menuId));
        return toResponse(menu);
    }

    @Override
    public FoodMenuResponse getMenuByDay(FoodMenu.DayOfWeek day) {
        FoodMenu menu = foodMenuRepository.findByDayOfWeek(day)
                .orElseThrow(() -> new ResourceNotFoundException("FoodMenu", "day", day));
        return toResponse(menu);
    }

    @Override
    public List<FoodMenuResponse> getAllMenus() {
        return foodMenuRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private FoodMenuResponse toResponse(FoodMenu m) {
        FoodMenuResponse r = new FoodMenuResponse();
        r.setMenuId(m.getMenuId());
        r.setDayOfWeek(m.getDayOfWeek());
        r.setBreakfast(m.getBreakfast());
        r.setLunch(m.getLunch());
        r.setDinner(m.getDinner());
        return r;
    }
}
