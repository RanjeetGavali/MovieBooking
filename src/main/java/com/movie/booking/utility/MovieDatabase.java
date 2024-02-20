package com.movie.booking.utility;
import com.movie.booking.model.Movie;
import com.movie.booking.model.ShowTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieDatabase {
    private static List<Movie> movies = new ArrayList<>();

    private static Long count = 1l;


    public static ShowTime createShowTime(Integer id,LocalDateTime time, int availableSeats) {
        return new ShowTime(id,time, availableSeats);
    }

    static {
        Movie movie1 = new Movie("Inception", "Sci-Fi");
        movie1.setId(count++);
        movie1.addShowTime(createShowTime(1,LocalDateTime.of(2024, 2, 17, 18, 0), 10)); // Example showtime at 6:00 PM with 10 seats
        movie1.addShowTime(createShowTime(2,LocalDateTime.of(2024, 2, 17, 20, 30), 15)); // Example showtime at 8:30 PM with 15 seats
        movies.add(movie1);

        Movie movie2 = new Movie("The Shawshank Redemption", "Drama");
        movie2.setId(count++);
        movie2.addShowTime(createShowTime(1,LocalDateTime.of(2024, 2, 17, 19, 15), 12));
        movie2.addShowTime(createShowTime(2,LocalDateTime.of(2024, 2, 17, 21, 45), 18));
        movies.add(movie2);

    }

    public static List<Movie> getAllMovies() {
        return movies;
    }

    public static Movie getMovieById(Long id) {
        for (Movie movie : movies) {
            if (movie.getId() == (id)) {
                return movie;
            }
        }
        return null;
    }
}

