package com.example.cinema.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateShowTimeRequest {

    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;

    public CreateShowTimeRequest() {}

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}