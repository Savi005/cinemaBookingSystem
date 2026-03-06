package com.example.cinema.dto;

import java.time.LocalDateTime;

public class ShowTimeResponse {

    private Long id;
    private LocalDateTime startTime;
    private Long movieId;

    public ShowTimeResponse(Long id, LocalDateTime startTime, Long movieId) {
        this.id = id;
        this.startTime = startTime;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Long getMovieId() {
        return movieId;
    }
}