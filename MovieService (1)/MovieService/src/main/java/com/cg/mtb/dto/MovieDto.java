package com.cg.mtb.dto;

public class MovieDto {

	private int movieId;
    private String movieName;
    private int ticketBasePrice;
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
	@Override
	public String toString() {
		return "MovieDto [movieId=" + movieId + ", movieName=" + movieName + ", ticketBasePrice=" + ticketBasePrice
				+ "]";
	}
	public MovieDto(int movieId, String movieName, int ticketBasePrice) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.ticketBasePrice = ticketBasePrice;
	}
	public MovieDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
