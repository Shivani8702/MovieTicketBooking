package com.cg.mtb.dto;

import jakarta.validation.constraints.*;

public class AddressDto {

    private int addressId;

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters")
    private String country;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
    private String state;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    private String city;

    @NotBlank(message = "Address line cannot be blank")
    @Size(min = 5, max = 255, message = "Address line must be between 5 and 255 characters")
    private String addressLine;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	@Override
	public String toString() {
		return "AddressDto [addressId=" + addressId + ", country=" + country + ", state=" + state + ", city=" + city
				+ ", addressLine=" + addressLine + "]";
	}

	public AddressDto(int addressId,
			@NotBlank(message = "Country cannot be blank") @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters") String country,
			@NotBlank(message = "State cannot be blank") @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters") String state,
			@NotBlank(message = "City cannot be blank") @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters") String city,
			@NotBlank(message = "Address line cannot be blank") @Size(min = 5, max = 255, message = "Address line must be between 5 and 255 characters") String addressLine) {
		super();
		this.addressId = addressId;
		this.country = country;
		this.state = state;
		this.city = city;
		this.addressLine = addressLine;
	}

	public AddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
