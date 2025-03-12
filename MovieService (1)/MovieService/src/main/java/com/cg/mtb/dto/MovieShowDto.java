package com.cg.mtb.dto;

import java.time.LocalDateTime;

public class MovieShowDto {

	private int showId;
    private int movieId;
    private int theatreId;
    private LocalDateTime showDatetime;
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
	
	public LocalDateTime getShowDatetime() {
		return showDatetime;
	}
	public void setShowDatetime(LocalDateTime showDatetime) {
		this.showDatetime = showDatetime;
	}
	
	
	public MovieShowDto(int showId, int movieId, int theatreId, LocalDateTime showDatetime) {
		super();
		this.showId = showId;
		this.movieId = movieId;
		this.theatreId = theatreId;
		this.showDatetime = showDatetime;
	}
	@Override
	public String toString() {
		return "MovieShowDto [showId=" + showId + ", movieId=" + movieId + ", theatreId=" + theatreId
				+ ", showDatetime=" + showDatetime + "]";
	}
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public MovieShowDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
