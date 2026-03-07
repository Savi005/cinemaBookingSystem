package com.example.cinema.exception;

public class MovieNotFoundException extends RuntimeException {
    
    public MovieNotFoundException(String message){
        super(message);
    }
}
