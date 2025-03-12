package com.cg.mtb.dto;

import java.time.LocalDate;

public class TicketBookingDto {

    private Long ticketId;
    private Long showId;
    private Long seatId;
    private int amount;
    private LocalDate purchaseDate;
    private String movieName;
    private String theatreName;

   
    public TicketBookingDto() {
    }

 
    public TicketBookingDto(Long ticketId, Long showId, Long seatId, int amount, LocalDate purchaseDate, 
                     String movieName, String theatreName) {
        this.ticketId = ticketId;
        this.showId = showId;
        this.seatId = seatId;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.movieName = movieName;
        this.theatreName = theatreName;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

	@Override
	public String toString() {
		return "TicketDTO [ticketId=" + ticketId + ", showId=" + showId + ", seatId=" + seatId + ", amount=" + amount
				+ ", purchaseDate=" + purchaseDate + ", movieName=" + movieName + ", theatreName=" + theatreName + "]";
	}
    
}
