package com.example.cinema.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<String> handleSeatException(SeatNotAvailableException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleSeatException(MovieNotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

}