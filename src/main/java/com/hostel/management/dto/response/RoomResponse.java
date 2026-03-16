package com.hostel.management.dto.response;
public class RoomResponse {
    private Long roomId; private String roomNumber; private Integer capacity; private Integer occupancy;
    private Long wardenId; private String wardenUsername;
    public RoomResponse() {}
    public Long getRoomId()         { return roomId; }         public void setRoomId(Long v)         { this.roomId = v; }
    public String getRoomNumber()   { return roomNumber; }     public void setRoomNumber(String v)   { this.roomNumber = v; }
    public Integer getCapacity()    { return capacity; }       public void setCapacity(Integer v)    { this.capacity = v; }
    public Integer getOccupancy()   { return occupancy; }      public void setOccupancy(Integer v)   { this.occupancy = v; }
    public Long getWardenId()       { return wardenId; }       public void setWardenId(Long v)       { this.wardenId = v; }
    public String getWardenUsername(){ return wardenUsername; } public void setWardenUsername(String v){ this.wardenUsername = v; }
}
