package com.example.cinema.dto.request;

public class CreateMovieRequest {

    private String title;
    private int durationMinutes;

    public CreateMovieRequest() {
    }

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