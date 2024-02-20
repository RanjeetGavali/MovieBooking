package com.movie.booking.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private Long id;
    private String title;
    private String genre;

    private List<ShowTime> showTimes = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<ShowTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    public void addShowTime(ShowTime showTime) {
        showTimes.add(showTime);
    }
}
