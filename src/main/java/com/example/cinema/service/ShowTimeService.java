package com.example.cinema.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.cinema.dto.CreateShowTimeRequest;
import com.example.cinema.dto.ShowTimeResponse;
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

    @Transactional
    public ShowTimeResponse createShowTime(Long movieId, CreateShowTimeRequest request){

        Movie movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new RuntimeException("Movie not found"));

        ShowTime showTime = new ShowTime();
        showTime.setStartTime(request.getStartTime());
        showTime.setMovie(movie);

        ShowTime savedShowTime = showTimeRepository.save(showTime);

        List<Seat> seats = new ArrayList<>();

        for(int i = 1; i <= 50; i++){
            Seat seat = new Seat();
            seat.setSeatnumber("A"+i);
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setShowTime(savedShowTime);
            seats.add(seat);
        }

        seatRepository.saveAll(seats);

        return new ShowTimeResponse(
                savedShowTime.getId(),
                savedShowTime.getStartTime(),
                movie.getId()
        );
    }

    public List<ShowTimeResponse> getShowTimesByMovie(Long movieId) {

        List<ShowTime> showTimes = showTimeRepository.findByMovieId(movieId);

        return showTimes.stream()
                .map(showTime -> new ShowTimeResponse(
                        showTime.getId(),
                        showTime.getStartTime(),
                        showTime.getMovie().getId()
                ))
                .collect(Collectors.toList());
    }
}