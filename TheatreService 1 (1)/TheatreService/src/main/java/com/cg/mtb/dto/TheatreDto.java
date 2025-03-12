package com.cg.mtb.dto;

import jakarta.validation.constraints.*;

public class TheatreDto {

    private int theatreId;

    @NotNull(message = "Address ID cannot be null")
    private int addressId;

    @NotBlank(message = "Theatre name cannot be blank")
    @Size(min = 2, max = 100, message = "Theatre name must be between 2 and 100 characters")
    private String theatreName;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;
    
    @NotBlank(message = "Screeen cannot be blank")
    @Size(min = 2, max = 50, message = "Screen name must be between 2 and 50 characters")
    private String screen;

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	

	@Override
	public String toString() {
		return "TheatreDto [theatreId=" + theatreId + ", addressId=" + addressId + ", theatreName=" + theatreName
				+ ", capacity=" + capacity + ", screen=" + screen + "]";
	}

	public TheatreDto(int theatreId, @NotNull(message = "Address ID cannot be null") int addressId,
			@NotBlank(message = "Theatre name cannot be blank") @Size(min = 2, max = 100, message = "Theatre name must be between 2 and 100 characters") String theatreName,
			@NotNull(message = "Capacity cannot be null") @Min(value = 1, message = "Capacity must be at least 1") int capacity,
			@NotBlank(message = "Screeen cannot be blank") @Size(min = 2, max = 50, message = "Screen name must be between 2 and 50 characters") String screen) {
		super();
		this.theatreId = theatreId;
		this.addressId = addressId;
		this.theatreName = theatreName;
		this.capacity = capacity;
		this.screen = screen;
	}

	public TheatreDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	

    
}
