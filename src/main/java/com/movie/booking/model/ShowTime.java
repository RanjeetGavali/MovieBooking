package com.movie.booking.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class ShowTime {

    private Integer id;
    private LocalDateTime time;

    private int availableSeats;

    private List<Integer> bookedSeats;

    public ShowTime(Integer id,LocalDateTime time, int availableSeats) {
        this.id = id;
        this.time = time;
        this.availableSeats = availableSeats;
        this.bookedSeats = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void bookSeat(int seatNumber) {
        bookedSeats.add(seatNumber);
        availableSeats--;
    }

    public boolean cancelSeat(int seatNumber) {
        boolean status = bookedSeats.remove(Integer.valueOf(seatNumber));
        if(status)
            availableSeats++;
        return status;
    }
}

