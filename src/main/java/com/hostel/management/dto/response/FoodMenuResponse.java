package com.hostel.management.dto.response;
import com.hostel.management.model.FoodMenu;
public class FoodMenuResponse {
    private Long menuId; private FoodMenu.DayOfWeek dayOfWeek;
    private String breakfast; private String lunch; private String dinner;
    public FoodMenuResponse() {}
    public Long getMenuId()              { return menuId; }    public void setMenuId(Long v)              { this.menuId = v; }
    public FoodMenu.DayOfWeek getDayOfWeek(){ return dayOfWeek; } public void setDayOfWeek(FoodMenu.DayOfWeek v){ this.dayOfWeek = v; }
    public String getBreakfast()         { return breakfast; } public void setBreakfast(String v)         { this.breakfast = v; }
    public String getLunch()             { return lunch; }     public void setLunch(String v)             { this.lunch = v; }
    public String getDinner()            { return dinner; }    public void setDinner(String v)            { this.dinner = v; }
}
