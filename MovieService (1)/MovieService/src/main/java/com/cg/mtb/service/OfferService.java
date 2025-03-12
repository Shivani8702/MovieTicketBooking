package com.cg.mtb.service;

import com.cg.mtb.dto.OfferDto;
import com.cg.mtb.entity.OfferEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OfferService {

    // Create a new offer
    OfferDto createOffer(OfferDto offerDto);

    // Get offer by its ID
    Optional<OfferDto> getOfferById(int offerId);

    // Get all offers
    Set<OfferDto> getAllOffers();

    // Update an existing offer
    //OfferDto updateOffer(int offerId, OfferDto offerDto);

    // Delete an offer by its ID
    void deleteOffer(int offerId);
}
