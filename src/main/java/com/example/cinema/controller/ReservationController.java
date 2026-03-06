package com.example.cinema.controller;

import org.springframework.web.bind.annotation.*;

import com.example.cinema.dto.ReservationResponse;
import com.example.cinema.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController (ReservationService reservationsService){
        this.reservationService = reservationsService;
    }

    @PostMapping("/seat/{seatId}")
    public ReservationResponse reservation(
        @PathVariable Long seatId,
        @RequestHeader("Idempotency-Key") String idempotencyKey)
    {
        return reservationService.reserveSeat(seatId, idempotencyKey);
    }
}