package com.hostel.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wardens")
public class Warden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardenId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Warden() {}

    public Warden(Long wardenId, User user) {
        this.wardenId = wardenId; this.user = user;
    }

    public Long getWardenId() { return wardenId; }
    public void setWardenId(Long wardenId) { this.wardenId = wardenId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
