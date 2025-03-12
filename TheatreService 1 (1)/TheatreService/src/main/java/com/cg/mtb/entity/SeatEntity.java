package com.cg.mtb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats", uniqueConstraints = @UniqueConstraint(columnNames = {"tier_id", "seat_number"}))
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int seatId;

    @ManyToOne
    @JoinColumn(name = "tier_id", nullable = false)
    private TierEntity tier;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    public SeatEntity() {}

    public SeatEntity(TierEntity tier, String seatNumber) {
        this.tier = tier;
        this.seatNumber = seatNumber;
    }

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public TierEntity getTier() {
		return tier;
	}

	public void setTier(TierEntity tier) {
		this.tier = tier;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public String toString() {
		return "SeatEntity [seatId=" + seatId + ", tier=" + tier + ", seatNumber=" + seatNumber + "]";
	}
	
   
}
