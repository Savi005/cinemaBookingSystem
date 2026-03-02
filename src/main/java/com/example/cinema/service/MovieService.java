package com.example.cinema.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.cinema.model.Movie;
import com.example.cinema.repository.MovieRepository;


@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Movie movieCreate(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

}
