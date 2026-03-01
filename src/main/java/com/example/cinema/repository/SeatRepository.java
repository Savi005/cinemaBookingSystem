package com.example.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cinema.model.Seat;
import com.example.cinema.model.SeatStatus;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByShowTimeId(Long showTimeId);

    Optional<Seat> findByIdAndStatus(Long id, SeatStatus status);
}