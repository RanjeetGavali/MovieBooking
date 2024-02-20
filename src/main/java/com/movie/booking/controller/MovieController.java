package com.movie.booking.controller;

import com.movie.booking.Service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @GetMapping("/all")
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(bookingServiceImpl.getAllMovies());
    }

    @PutMapping("/available/seat/{movieId}")
    public ResponseEntity<?> getAvailableSeat(@PathVariable Long movieId,@RequestParam Integer showId ) {
        return ResponseEntity.ok(bookingServiceImpl.getAvailableSeats(movieId,showId));
    }

    @PutMapping("/book/seat/{movieId}")
    public ResponseEntity<?> bookTicketByMovieIdAndShowId(@PathVariable Long movieId,@RequestParam Integer showId ) {
        return ResponseEntity.ok(bookingServiceImpl.bookTicket(movieId,showId));
    }

    @PutMapping("/cancel/booing/{movieId}")
    public ResponseEntity<?> cancleBookTicketByMovieIdAndShowId(@PathVariable Long movieId,@RequestParam Integer showId,@RequestParam Integer seatNumber ) {
        return ResponseEntity.ok(bookingServiceImpl.cancelTicket(movieId,showId,seatNumber));
    }

    @GetMapping("/display/status/{movieId}")
    public ResponseEntity<?> displaySeatStatus(@PathVariable Long movieId ) {
        return ResponseEntity.ok(bookingServiceImpl.displaySeatStatus(movieId));
    }


}
