package com.example.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cinema.model.ShowTime;
import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {

    List<ShowTime> findByMovieId(Long movieId);
}