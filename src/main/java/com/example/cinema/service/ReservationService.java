package com.example.cinema.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import com.example.cinema.dto.ReservationResponse;
import com.example.cinema.model.*;
import com.example.cinema.repository.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    public ReservationService(
            SeatRepository seatRepository,
            ReservationRepository reservationRepository) {

        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public ReservationResponse reserveSeat(Long seatId, String idempotencyKey) {

        // Idempotency check
        Reservation existing = reservationRepository
                .findByIdempotencyKey(idempotencyKey)
                .orElse(null);

        if (existing != null) {
            return new ReservationResponse(
                    existing.getId(),
                    existing.getSeat().getId(),
                    existing.getReservedAt()
            );
        }

        Seat seat = seatRepository.findByIdAndStatus(seatId, SeatStatus.AVAILABLE)
                .orElseThrow(() -> new RuntimeException("Seat not available"));

        seat.setStatus(SeatStatus.RESERVED);

        Reservation reservation = new Reservation();
        reservation.setSeat(seat);
        reservation.setIdempotencyKey(idempotencyKey);
        reservation.setReservedAt(LocalDateTime.now());

        Reservation saved = reservationRepository.save(reservation);

        return new ReservationResponse(
                saved.getId(),
                saved.getSeat().getId(),
                saved.getReservedAt()
        );
    }
}