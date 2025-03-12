package com.cg.mtb.entity;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "movie")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_name", nullable = false, length = 255)
    private String movieName;

    @Column(name = "ticket_base_price", nullable = false)
    private int ticketBasePrice;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<MovieShowEntity> movieShows;

    public MovieEntity() {
        super();
    }

    public MovieEntity(int movieId, String movieName, int ticketBasePrice, Set<MovieShowEntity> movieShows) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.ticketBasePrice = ticketBasePrice;
        this.movieShows = movieShows;
    }

    @Override
    public String toString() {
        return "MovieEntity [movieId=" + movieId + ", movieName=" + movieName + ", ticketBasePrice=" + ticketBasePrice
                + ", movieShows=" + movieShows + "]";
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getTicketBasePrice() {
        return ticketBasePrice;
    }

    public void setTicketBasePrice(int ticketBasePrice) {
        this.ticketBasePrice = ticketBasePrice;
    }

    public Set<MovieShowEntity> getMovieShows() {
        return movieShows;
    }

    public void setMovieShows(Set<MovieShowEntity> movieShows) {
        this.movieShows = movieShows;
    }
}
