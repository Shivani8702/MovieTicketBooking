package com.cg.mtb.dto;

import jakarta.validation.constraints.*;

public class TierDto {

    private int tierId;

    @NotNull(message = "Theatre ID cannot be null")
    private int theatreId;

    @NotBlank(message = "Tier name cannot be blank")
    @Size(min = 2, max = 50, message = "Tier name must be between 2 and 50 characters")
    private String tierName;

    @NotNull(message = "Ticket price multiplier cannot be null")
    @Min(value = 0, message = "Ticket price multiplier must be at least 0")
    private Double ticketPriceMultiplier;

	public int getTierId() {
		return tierId;
	}

	public void setTierId(int tierId) {
		this.tierId = tierId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public String getTierName() {
		return tierName;
	}

	public void setTierName(String tierName) {
		this.tierName = tierName;
	}

	public Double getTicketPriceMultiplier() {
		return ticketPriceMultiplier;
	}

	public void setTicketPriceMultiplier(Double ticketPriceMultiplier) {
		this.ticketPriceMultiplier = ticketPriceMultiplier;
	}

	@Override
	public String toString() {
		return "TierDto [tierId=" + tierId + ", theatreId=" + theatreId + ", tierName=" + tierName
				+ ", ticketPriceMultiplier=" + ticketPriceMultiplier + "]";
	}

	public TierDto(int tierId, @NotNull(message = "Theatre ID cannot be null") int theatreId,
			@NotBlank(message = "Tier name cannot be blank") @Size(min = 2, max = 50, message = "Tier name must be between 2 and 50 characters") String tierName,
			@NotNull(message = "Ticket price multiplier cannot be null") @Min(value = 0, message = "Ticket price multiplier must be at least 0") Double ticketPriceMultiplier) {
		super();
		this.tierId = tierId;
		this.theatreId = theatreId;
		this.tierName = tierName;
		this.ticketPriceMultiplier = ticketPriceMultiplier;
	}

	public TierDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
}
