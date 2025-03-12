package com.cg.mtb.entity;
 
import java.time.LocalDateTime;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
 
@Entity
@Table(name = "movie_show")
public class MovieShowEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private int showId;
 
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonManagedReference
    private MovieEntity movie;
 
//   @ManyToOne
  //  @JoinColumn(name = "theatre_id", nullable = false)
//    private Theatre theatre; 
    @Column(name="theatre_id",nullable=false)
    private int theatreId;
 
    @Column(name = "show_datetime", nullable = false)
    private LocalDateTime showDatetime;
 
    
//    @OneToMany(mappedBy = "movieShow")
    @OneToMany(mappedBy = "movieShow", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferEntity> offers;

 
    public MovieShowEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	@Override
	public String toString() {
		return "MovieShowEntity [showId=" + showId + ", movie=" + movie + ", theaterId=" + theatreId + ", showDatetime="
				+ showDatetime + "]";
	}

 
	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
 
	public MovieShowEntity(int showId, MovieEntity movie, int theaterId, LocalDateTime showDatetime) {
		super();
		this.showId = showId;
		this.movie = movie;
		this.theatreId = theatreId;
		this.showDatetime = showDatetime;
	}
 
	public int getShowId() {
        return showId;
    }
 
    public void setShowId(int showId) {
        this.showId = showId;
    }
 
    public MovieEntity getMovie() {
        return movie;
    }
 
    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }
 
    
 
    public LocalDateTime getShowDatetime() {
        return showDatetime;
    }
 
    public void setShowDatetime(LocalDateTime showDatetime) {
        this.showDatetime = showDatetime;
    }
 
//    public Set<OfferEntity> getOffers() {
//        return offers;
//    }
//
//    public void setOffers(Set<OfferEntity> offers) {
//        this.offers = offers;
//    }
}