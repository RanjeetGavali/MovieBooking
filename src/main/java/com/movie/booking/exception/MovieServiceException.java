package com.movie.booking.exception;

public class MovieServiceException extends RuntimeException {

    private int status;

    public MovieServiceException(String message, int status) {
        super(message);
        this.status = status;

    }

    public int getStatus() {
        return status;
    }
}
