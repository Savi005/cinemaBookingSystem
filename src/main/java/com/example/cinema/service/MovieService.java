package com.example.cinema.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.example.cinema.dto.CreateMovieRequest;
import com.example.cinema.dto.MovieResponse;
import com.example.cinema.model.Movie;
import com.example.cinema.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public MovieResponse movieCreate(CreateMovieRequest request){

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDurationMinutes(request.getDurationMinutes());

        Movie savedMovie = movieRepository.save(movie);

        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle(),
                savedMovie.getDurationMinutes()
        );
    }

    public List<MovieResponse> getAllMovies(){

        List<Movie> movies = movieRepository.findAll();

        return movies.stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDurationMinutes()
                ))
                .collect(Collectors.toList());
    }
}