package com.example.cinema.dto;

public class ReservationRequest {

    private Long showTimeId;
    private String seatNumber;
    private String idempotencyKey;

    public Long getShowTimeId() {
        return showTimeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }
}