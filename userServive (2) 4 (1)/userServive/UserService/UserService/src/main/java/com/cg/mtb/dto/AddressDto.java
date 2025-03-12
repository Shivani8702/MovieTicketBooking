package com.cg.mtb.dto;

import java.io.Serializable;

public class AddressDto implements Serializable {
    private int addressId;
    private String country;
    private String state;
    private String city;
    private String addressLine;

    public AddressDto() {}

    public AddressDto(int addressId, String country, String state, String city, String addressLine) {
        this.addressId = addressId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.addressLine = addressLine;
    }

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
}
