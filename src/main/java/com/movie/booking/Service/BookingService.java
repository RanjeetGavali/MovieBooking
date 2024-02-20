package com.movie.booking.Service;

import com.movie.booking.model.Movie;

import java.util.List;
import java.util.Map;

public interface BookingService {

    public String bookTicket(Long movieId, Integer showId);

    public String cancelTicket(Long movieId, Integer showId, int seatNumber);

    public int getAvailableSeats(Long movieId, int showId);

    public Map<String, Map<String, Object>> displaySeatStatus(Long movieId);

    public List<Movie> getAllMovies();

}
