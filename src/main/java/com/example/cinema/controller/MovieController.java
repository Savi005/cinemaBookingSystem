package com.example.cinema.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.validation.Valid;

import com.example.cinema.dto.CreateMovieRequest;
import com.example.cinema.dto.MovieResponse;
import com.example.cinema.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping
    public MovieResponse createMovie(@Valid @RequestBody CreateMovieRequest request) {
        return movieService.movieCreate(request);
    }

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }
}