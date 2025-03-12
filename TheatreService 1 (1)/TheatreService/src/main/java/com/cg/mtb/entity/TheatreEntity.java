package com.cg.mtb.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theatre")
public class TheatreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private int theatreId;

    @ManyToOne(fetch = FetchType.LAZY) // ✅ Lazy fetching for better performance
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @Column(name = "theatre_name", nullable = false)
    private String theatreName;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "screen", nullable = false)
    private String screen;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<TierEntity> tiers = new ArrayList<>();

    // ✅ Constructors
    public TheatreEntity() {}

    public TheatreEntity(AddressEntity address, String theatreName, int capacity, String screen) {
        this.address = address;
        this.theatreName = theatreName;
        this.capacity = capacity;
        this.screen = screen;
    }

    // ✅ Getters and Setters
    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
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

    public List<TierEntity> getTiers() {
        return tiers;
    }

    public void setTiers(List<TierEntity> tiers) {
        this.tiers.clear();
        if (tiers != null) {
            for (TierEntity tier : tiers) {
                addTier(tier);
            }
        }
    }

    public void addTier(TierEntity tier) {
        tiers.add(tier);
        tier.setTheatre(this);
    }

    public void removeTier(TierEntity tier) {
        tiers.remove(tier);
        tier.setTheatre(null);
    }

    // ✅ toString() (Avoids printing all tiers to prevent recursion)
    @Override
    public String toString() {
        return "TheatreEntity [theatreId=" + theatreId + ", theatreName=" + theatreName
                + ", capacity=" + capacity + ", screen=" + screen + "]";
    }
}
