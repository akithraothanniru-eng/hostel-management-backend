package com.hostel.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false, unique = true, length = 20)
    private String roomNumber;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warden_id")
    private Warden warden;

    public Room() {}

    public Room(Long roomId, String roomNumber, Integer capacity, Warden warden) {
        this.roomId = roomId; this.roomNumber = roomNumber;
        this.capacity = capacity; this.warden = warden;
    }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public Warden getWarden() { return warden; }
    public void setWarden(Warden warden) { this.warden = warden; }
}
