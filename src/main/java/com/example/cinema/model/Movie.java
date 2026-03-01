package com.example.cinema.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private int durationMinutes;
    
    @OneToMany(mappedBy = "movie")
    private List<ShowTime> showtimes;

      public Movie() {
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDurationMinutes(int durationMinutes){
        this.durationMinutes = durationMinutes;
    }


}