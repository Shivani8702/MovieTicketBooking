package com.cg.mtb.dto;

import jakarta.validation.constraints.*;

public class SeatDto {

    private int seatId;

    @NotNull(message = "Tier ID cannot be null")
    private int tierId;

    @NotBlank(message = "Seat number cannot be blank")
    @Pattern(regexp = "^[A-Z]\\d+$", message = "Seat number must be in format: Letter followed by digits (e.g., A10)")
    private String seatNumber;

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getTierId() {
		return tierId;
	}

	public void setTierId(int tierId) {
		this.tierId = tierId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public String toString() {
		return "SeatDto [seatId=" + seatId + ", tierId=" + tierId + ", seatNumber=" + seatNumber + "]";
	}

	public SeatDto(int seatId, @NotNull(message = "Tier ID cannot be null") int tierId,
			@NotBlank(message = "Seat number cannot be blank") @Pattern(regexp = "^[A-Z]\\d+$", message = "Seat number must be in format: Letter followed by digits (e.g., A10)") String seatNumber) {
		super();
		this.seatId = seatId;
		this.tierId = tierId;
		this.seatNumber = seatNumber;
	}

	public SeatDto() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
