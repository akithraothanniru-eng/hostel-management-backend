package com.hostel.management.service;
import com.hostel.management.dto.request.RoomRequest;
import com.hostel.management.dto.response.RoomResponse;
import java.util.List;
public interface RoomService {
    RoomResponse createRoom(RoomRequest request);
    RoomResponse updateRoom(Long roomId, RoomRequest request);
    void deleteRoom(Long roomId);
    RoomResponse getRoomById(Long roomId);
    List<RoomResponse> getAllRooms();
    List<RoomResponse> getAvailableRooms();
}
