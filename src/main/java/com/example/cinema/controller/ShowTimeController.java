package com.example.cinema.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.validation.Valid;

import com.example.cinema.dto.CreateShowTimeRequest;
import com.example.cinema.dto.ShowTimeResponse;
import com.example.cinema.service.ShowTimeService;

@RestController
@RequestMapping("/showtimes")
public class ShowTimeController {

    private final ShowTimeService showTimeService;

    public ShowTimeController(ShowTimeService showTimeService) {
        this.showTimeService = showTimeService;
    }

    @PostMapping("/{movieId}")
    public ShowTimeResponse creatShowTime(
        @PathVariable Long movieId,
        @Valid @RequestBody CreateShowTimeRequest request
    ){
        return showTimeService.createShowTime(movieId, request);
    }

    @GetMapping("/movie/{movieId}")
    public List<ShowTimeResponse> getShowTimesByMovie(@PathVariable Long movieId) {
        return showTimeService.getShowTimesByMovie(movieId);
    }
}