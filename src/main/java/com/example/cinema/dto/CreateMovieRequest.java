package com.example.cinema.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateMovieRequest {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int durationMinutes;

    public CreateMovieRequest() {}

    public String getTitle() {
        return title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}