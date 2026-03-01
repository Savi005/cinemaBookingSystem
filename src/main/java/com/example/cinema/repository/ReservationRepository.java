package com.example.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cinema.model.Reservation;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByIdempotencyKey(String idempotencyKey);
}