package com.example.cinema.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.example.cinema.model.*;
import com.example.cinema.repository.*;

@Service
public class ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final SeatRepository seatRepository;

    public ShowTimeService(
            ShowTimeRepository showTimeRepository,
            MovieRepository movieRepository,
            SeatRepository seatRepository) {

        this.showTimeRepository = showTimeRepository;
        this.movieRepository = movieRepository;
        this.seatRepository = seatRepository;
    }
    //get the movie time form user and save it to make an id and then get it gain to make seats 
    @Transactional
    public ShowTime createShowTime(Long movieId, ShowTime showTime){

        Movie movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new RuntimeException("Movie not found"));

        showTime.setMovie(movie);
        ShowTime savedShowTime = showTimeRepository.save(showTime);

        List<Seat> seats = new ArrayList<>();

        for(int i = 1; i<=50;i++){
            Seat seat = new Seat();
            seat.setSeatnumber("A"+i);
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setShowTime(savedShowTime);
            seats.add(seat);
        }
        seatRepository.saveAll(seats);
        return showTime;
    }
    public List<ShowTime> getShowTimesByMovie(Long movieId) {
        return showTimeRepository.findByMovieId(movieId);
    }

}