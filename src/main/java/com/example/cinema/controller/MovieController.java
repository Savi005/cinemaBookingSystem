package com.example.cinema.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.cinema.model.Movie;
import com.example.cinema.service.MovieService;



@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.movieCreate(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    
    
}
