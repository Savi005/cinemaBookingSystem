package com.example.cinema.dto;

import com.example.cinema.model.SeatStatus;

public class SeatResponse {

    private String seatNumber;
    private SeatStatus status;

    public SeatResponse(String seatNumber, SeatStatus status) {
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }
}