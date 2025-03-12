//package com.cg.mtb.dto;
//
//import com.cg.mtb.entity.MovieShowEntity;
//
//public class OfferDto {
//
//	private int offerId;
//    private int showId;
//    private String offerDetails;
//	public int getOfferId() {
//		return offerId;
//	}
//	public void setOfferId(int offerId) {
//		this.offerId = offerId;
//	}
//	public int getShowId() {
//		return showId;
//	}
//	public void setShowId(int showId) {
//		this.showId = showId;
//	}
//	public String getOfferDetails() {
//		return offerDetails;
//	}
//	public void setOfferDetails(String offerDetails) {
//		this.offerDetails = offerDetails;
//	}
//	@Override
//	public String toString() {
//		return "OfferDto [offerId=" + offerId + ", showId=" + showId + ", offerDetails=" + offerDetails + "]";
//	}
//	public OfferDto(int offerId, int showId, String offerDetails) {
//		super();
//		this.offerId = offerId;
//		this.showId = showId;
//		this.offerDetails = offerDetails;
//	}
//	public OfferDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public void setMovieShow(MovieShowEntity movieShow) {
//		// TODO Auto-generated method stub
//		
//	}
//    
//    
//}

package com.cg.mtb.dto;

import com.cg.mtb.entity.MovieShowEntity;

public class OfferDto {

    private int offerId;
    private MovieShowDto movieShow;  // Should be MovieShowDto instead of just showId
    private String offerDetails;

    // Getters and setters
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public MovieShowDto getMovieShow() {
        return movieShow;
    }

    public void setMovieShow(MovieShowDto movieShow) {
        this.movieShow = movieShow;
    }

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    @Override
    public String toString() {
        return "OfferDto [offerId=" + offerId + ", movieShow=" + movieShow + ", offerDetails=" + offerDetails + "]";
    }

    public OfferDto(int offerId, MovieShowDto movieShow, String offerDetails) {
        this.offerId = offerId;
        this.movieShow = movieShow;
        this.offerDetails = offerDetails;
    }

    public OfferDto() {
    }
}
