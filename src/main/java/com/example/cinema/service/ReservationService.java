package com.example.cinema.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import com.example.cinema.dto.ReservationResponse;
import com.example.cinema.exception.SeatNotAvailableException;
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
    public ReservationResponse reserveSeat(
            Long showTimeId,
            String seatNumber,
            String idempotencyKey) {

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

        Seat seat = seatRepository.findByShowTimeIdAndSeatNumberAndStatus(
                        showTimeId,
                        seatNumber,
                        SeatStatus.AVAILABLE)
                .orElseThrow(() ->
                        new SeatNotAvailableException("Seat not available"));
        System.out.println(seat);
        // reserve seat
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