package com.example.cinema.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idempotencyKey;

    private LocalDateTime reservedAt;

    @OneToOne
    @JoinColumn(name = "seat_id", nullable = false, unique = true)
    private Seat seat;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}