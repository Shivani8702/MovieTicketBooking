package com.cg.mtb.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tier")
public class TierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tier_id")
    private int tierId;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private TheatreEntity theatre;
    
    @OneToMany(mappedBy = "tier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatEntity> seats;

    @Column(name = "tier_name", nullable = false)
    private String tierName;

    @Column(name = "ticket_price_multiplier", nullable = false)
    private Double ticketPriceMultiplier;

    public TierEntity() {}

    public TierEntity(TheatreEntity theatre, String tierName, Double ticketPriceMultiplier) {
        this.theatre = theatre;
        this.tierName = tierName;
        this.ticketPriceMultiplier = ticketPriceMultiplier;
    }

    public int getTierId() {
        return tierId;
    }

    public void setTierId(int tierId) {
        this.tierId = tierId;
    }

    public TheatreEntity getTheatre() {
        return theatre;
    }

    public void setTheatre(TheatreEntity theatre) {
        this.theatre = theatre;
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
        return "TierEntity [tierId=" + tierId + ", tierName=" + tierName
                + ", ticketPriceMultiplier=" + ticketPriceMultiplier + "]";
    }
}
