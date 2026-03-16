package com.hostel.management.dto.request;
import jakarta.validation.constraints.*;
public class RoomRequest {
    @NotBlank private String roomNumber;
    @NotNull @Min(1) private Integer capacity;
    private Long wardenId;
    public RoomRequest() {}
    public String getRoomNumber() { return roomNumber; } public void setRoomNumber(String v) { this.roomNumber = v; }
    public Integer getCapacity()  { return capacity; }   public void setCapacity(Integer v)  { this.capacity = v; }
    public Long getWardenId()     { return wardenId; }   public void setWardenId(Long v)     { this.wardenId = v; }
}
