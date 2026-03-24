package com.hostel.management.service.impl;

import com.hostel.management.dto.request.RoomRequest;
import com.hostel.management.dto.response.RoomResponse;
import com.hostel.management.exception.BadRequestException;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.Room;
import com.hostel.management.model.Warden;
import com.hostel.management.repository.RoomRepository;
import com.hostel.management.repository.StudentRepository;
import com.hostel.management.repository.WardenRepository;
import com.hostel.management.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final WardenRepository wardenRepository;
    private final StudentRepository studentRepository;

    public RoomServiceImpl(RoomRepository roomRepository, WardenRepository wardenRepository,
                            StudentRepository studentRepository) {
        this.roomRepository = roomRepository;
        this.wardenRepository = wardenRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public RoomResponse createRoom(RoomRequest request) {
        if (roomRepository.existsByRoomNumber(request.getRoomNumber()))
            throw new BadRequestException("Room number already exists: " + request.getRoomNumber());

        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());

        if (request.getWardenId() != null) {
            Warden warden = wardenRepository.findById(request.getWardenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Warden", "id", request.getWardenId()));
            room.setWarden(warden);
        }

        roomRepository.save(room);
        return toResponse(room);
    }

    @Override
    @Transactional
    public RoomResponse updateRoom(Long roomId, RoomRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        if (!room.getRoomNumber().equals(request.getRoomNumber()) &&
                roomRepository.existsByRoomNumber(request.getRoomNumber()))
            throw new BadRequestException("Room number already exists: " + request.getRoomNumber());

        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());

        if (request.getWardenId() != null) {
            Warden warden = wardenRepository.findById(request.getWardenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Warden", "id", request.getWardenId()));
            room.setWarden(warden);
        } else {
            room.setWarden(null);
        }

        roomRepository.save(room);
        return toResponse(room);
    }

    @Override
    @Transactional
    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
        roomRepository.delete(room);
    }

    @Override
    public RoomResponse getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
        return toResponse(room);
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getAvailableRooms() {
        return roomRepository.findAvailableRooms().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private RoomResponse toResponse(Room r) {
        RoomResponse resp = new RoomResponse();
        resp.setRoomId(r.getRoomId());
        resp.setRoomNumber(r.getRoomNumber());
        resp.setCapacity(r.getCapacity());
        resp.setOccupancy(studentRepository.findByRoomRoomId(r.getRoomId()).size());

        // Safely access lazy warden — guarded null check
        if (r.getWarden() != null) {
            Warden w = r.getWarden();
            resp.setWardenId(w.getWardenId());
            // User is EAGER on Warden so this is safe inside a transaction
            if (w.getUser() != null) {
                resp.setWardenUsername(w.getUser().getUsername());
            }
        }
        return resp;
    }
}