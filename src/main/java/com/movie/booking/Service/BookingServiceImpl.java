package com.movie.booking.Service;

import com.movie.booking.exception.MovieServiceException;
import com.movie.booking.model.Movie;
import com.movie.booking.model.ShowTime;
import com.movie.booking.utility.MovieDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {

    @Override
    public String bookTicket(Long movieId, Integer showId) {
        try {
            Movie movie = MovieDatabase.getMovieById(movieId);
            if (movie == null) {
                return "Movie does not exists";
            }
            ShowTime showTime = movie.getShowTimes().get(showId - 1);
            int seatNumber = findClosestAvailableSeat(showTime);

            if (seatNumber != -1) {
                showTime.bookSeat(seatNumber);
                return "Booking confirmed - Movie: " + movie.getTitle() +
                        ", Genre: " + movie.getGenre() + ", Showtime: " + showTime.getTime() + ", Seat: " + seatNumber;
            } else {
                return "Sorry, no available seats for the selected showtime.";
            }
        } catch (Exception e) {
            throw new MovieServiceException("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public String cancelTicket(Long movieId, Integer showId, int seatNumber) {
        try {
            Movie movie = MovieDatabase.getMovieById(movieId);
            if (movie == null) {
                return "Movie does not exists";
            }
            ShowTime showTime = movie.getShowTimes().get(showId - 1);
            if (showTime.cancelSeat(seatNumber)) {
                return "Ticket canceled - Movie: " + movie.getTitle() +
                        ", Genre: " + movie.getGenre() + ", Showtime: " + showTime.getTime() + ", Seat: " + seatNumber;
            }

            return "incorrect seat number";
        } catch (Exception e) {
            throw new MovieServiceException("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public int getAvailableSeats(Long movieId, int showId) {
        try {
            Movie movie = MovieDatabase.getMovieById(movieId);
            if (movie == null) {
                return -1;
            }
           int size = movie.getShowTimes().size();
            if(size < showId-1) {
                ShowTime showTime = movie.getShowTimes().get(showId - 1);
                return showTime.getAvailableSeats();
            } else {
                throw new MovieServiceException("invalid show id", HttpStatus.BAD_REQUEST.value());
            }
        } catch (MovieServiceException m) {
            throw new MovieServiceException("invalid show Id", HttpStatus.BAD_REQUEST.value());
        } catch (Exception e) {
            throw new MovieServiceException("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public Map<String, Map<String, Object>> displaySeatStatus(Long movieId) {
        try {
            Movie movie = MovieDatabase.getMovieById(movieId);
            Map<String, Map<String, Object>> statusMap = new HashMap<>();
            if (movie != null) {
                for (int i = 0; i < movie.getShowTimes().size(); i++) {
                    Map<String, Object> info = new HashMap<>();
                    ShowTime showTime = movie.getShowTimes().get(i);
                    info.put("Available Seats", showTime.getAvailableSeats());
                    info.put(" Booked Seats", showTime.getBookedSeats());
                    info.put(" Showtime", showTime.getTime());
                    statusMap.put(String.valueOf(showTime.getId()), info);
                }
                return statusMap;
            } else {
                throw new MovieServiceException("Movie does not exists", HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            throw new MovieServiceException("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        return MovieDatabase.getAllMovies();
    }

    private int findClosestAvailableSeat(ShowTime showTime) {
        int totalSeats = showTime.getAvailableSeats();
        List<Integer> bookedSeats = showTime.getBookedSeats();

        for (int seat = 1; seat <= totalSeats+bookedSeats.size(); seat++) {
            if (!bookedSeats.contains(seat)) {
                return seat;
            }
        }

        return -1; // No available seats
    }
}
