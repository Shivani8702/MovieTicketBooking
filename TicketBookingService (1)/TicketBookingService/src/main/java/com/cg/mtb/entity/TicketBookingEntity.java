package com.cg.mtb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class TicketBookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private Long showId;
    private Long seatId;
    private int amount;
    private LocalDate purchaseDate;

   
    public TicketBookingEntity() {
    }

   
    public TicketBookingEntity(Long ticketId, Long showId, Long seatId, int amount, LocalDate purchaseDate) {
        this.ticketId = ticketId;
        this.showId = showId;
        this.seatId = seatId;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
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

	@Override
	public String toString() {
		return "TicketBookingEntity [ticketId=" + ticketId + ", showId=" + showId + ", seatId=" + seatId + ", amount="
				+ amount + ", purchaseDate=" + purchaseDate + "]";
	}
}
