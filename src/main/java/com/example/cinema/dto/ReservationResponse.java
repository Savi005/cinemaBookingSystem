package com.example.cinema.dto;

import java.time.LocalDateTime;

public class ReservationResponse {

    private Long id;
    private Long seatId;
    private LocalDateTime reservedAt;

    public ReservationResponse(Long id, Long seatId, LocalDateTime reservedAt) {
        this.id = id;
        this.seatId = seatId;
        this.reservedAt = reservedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getSeatId() {
        return seatId;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }
}