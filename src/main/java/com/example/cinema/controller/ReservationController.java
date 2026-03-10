package com.example.cinema.controller;

import org.springframework.web.bind.annotation.*;

import com.example.cinema.dto.ReservationRequest;
import com.example.cinema.dto.ReservationResponse;
import com.example.cinema.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationResponse reserve(@RequestBody ReservationRequest request) {

        return reservationService.reserveSeat(
                request.getShowTimeId(),
                request.getSeatNumber(),
                request.getIdempotencyKey()
        );
    }
}