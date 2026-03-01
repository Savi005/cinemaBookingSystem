package com.example.cinema.model;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @ManyToOne
    @JoinColumn(name = "showTime_id", nullable = false)
    private ShowTime showTime;

    public Seat() {

    }

    public Long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus(){
        return status;
    }

    public ShowTime getShowTime(){
        return showTime;
    }

    public void setSeatnumber(String seatNumber){
        this.seatNumber = seatNumber;
    }

    public void setStatus(SeatStatus status){
        this.status = status;
    }

    public void setShowTime(ShowTime showTime){
        this.showTime = showTime;
    }

}
