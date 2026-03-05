package com.example.cinema.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.cinema.model.ShowTime;
import com.example.cinema.service.ShowTimeService;


@RestController
@RequestMapping("/showtimes")
public class ShowTimeController {
    private final ShowTimeService showTimeService;

    public ShowTimeController(ShowTimeService showTimeService) {
        this.showTimeService = showTimeService;
    }

    @PostMapping("/{movieId}")
    
    public ShowTime creatShowTime(
        @PathVariable Long movieId,
        @RequestBody ShowTime showTime
    ){
        return showTimeService.createShowTime(movieId, showTime);
    }
    @GetMapping("/movie/{movieId}")
    public List<ShowTime> getShowTimesByMovie(@PathVariable Long movieId) {
        return showTimeService.getShowTimesByMovie(movieId);
    }
    
}
