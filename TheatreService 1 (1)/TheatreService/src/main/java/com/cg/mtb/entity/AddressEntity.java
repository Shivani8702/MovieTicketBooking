package com.cg.mtb.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address_line", nullable = false)
    private String addressLine;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TheatreEntity> theatres;

    public AddressEntity() {}

    public AddressEntity(String country, String state, String city, String addressLine) {
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

    public List<TheatreEntity> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<TheatreEntity> theatres) {
        this.theatres = theatres;
    }

    @Override
    public String toString() {
        return "AddressEntity [addressId=" + addressId + ", country=" + country + ", state=" + state + ", city=" + city
                + ", addressLine=" + addressLine + "]";
    }
}
