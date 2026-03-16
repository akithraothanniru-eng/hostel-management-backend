package com.hostel.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "food_menu")
public class FoodMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private DayOfWeek dayOfWeek;

    @Column(length = 500)
    private String breakfast;

    @Column(length = 500)
    private String lunch;

    @Column(length = 500)
    private String dinner;

    public enum DayOfWeek { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    public FoodMenu() {}

    public FoodMenu(Long menuId, DayOfWeek dayOfWeek, String breakfast, String lunch, String dinner) {
        this.menuId = menuId; this.dayOfWeek = dayOfWeek;
        this.breakfast = breakfast; this.lunch = lunch; this.dinner = dinner;
    }

    public Long getMenuId() { return menuId; }
    public void setMenuId(Long menuId) { this.menuId = menuId; }
    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public String getBreakfast() { return breakfast; }
    public void setBreakfast(String breakfast) { this.breakfast = breakfast; }
    public String getLunch() { return lunch; }
    public void setLunch(String lunch) { this.lunch = lunch; }
    public String getDinner() { return dinner; }
    public void setDinner(String dinner) { this.dinner = dinner; }
}
