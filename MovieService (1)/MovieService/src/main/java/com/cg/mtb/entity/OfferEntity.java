package com.cg.mtb.entity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "offers")
public class OfferEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private int offerId;
 
    @ManyToOne
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @JoinColumn(name = "show_id", nullable = false)
    private MovieShowEntity movieShow;
 
    @Column(name = "offer_details", length = 255)
    private String offerDetails;

 
    public OfferEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

 
	public OfferEntity(int offerId, MovieShowEntity movieShow, String offerDetails) {
		super();
		this.offerId = offerId;
		this.movieShow = movieShow;
		this.offerDetails = offerDetails;
	}
 
 
	
    public int getOfferId() {
        return offerId;
    }
 
    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }
 
    public MovieShowEntity getMovieShow() {
        return movieShow;
    }
 
    public void setMovieShow(MovieShowEntity movieShow) {
        this.movieShow = movieShow;
    }
 
    public String getOfferDetails() {
        return offerDetails;
    }
 
    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }
}