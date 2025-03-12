package com.cg.mtb.dto;

import java.time.LocalDateTime;

public class MovieShowDto {
    private int showId;
    private int movieId;
    private int theatreId;
    private LocalDateTime showDatetime;

    // Getters & Setters
    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public LocalDateTime getShowDatetime() {
        return showDatetime;
    }

    public void setShowDatetime(LocalDateTime showDatetime) {
        this.showDatetime = showDatetime;
    }
}

