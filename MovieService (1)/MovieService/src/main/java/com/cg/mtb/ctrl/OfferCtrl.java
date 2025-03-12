package com.cg.mtb.ctrl;

import com.cg.mtb.dto.OfferDto;
import com.cg.mtb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/offers")
public class OfferCtrl {

    private final OfferService offerService;

    @Autowired
    public OfferCtrl(OfferService offerService) {
        this.offerService = offerService;
    }

    // Create a new offer
    @PostMapping
    public ResponseEntity<OfferDto> createOffer(@RequestBody OfferDto offerDto) {
        OfferDto createdOffer = offerService.createOffer(offerDto);  // Use the service to create the offer
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);  // Return the created offer with a 201 status
    }

    // Get an offer by its ID
    @GetMapping("/{offerId}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable int offerId) {
        Optional<OfferDto> offerDtoOpt = offerService.getOfferById(offerId);  // Get the offer using service
        return offerDtoOpt
                .map(offerDto -> new ResponseEntity<>(offerDto, HttpStatus.OK))  // Return the offer with 200 status if present
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Return 404 if offer not found
    }

    // Get all offers
    @GetMapping
    public ResponseEntity<Set<OfferDto>> getAllOffers() {
        Set<OfferDto> offerDtos = offerService.getAllOffers();  // Get all offers using service
        return new ResponseEntity<>(offerDtos, HttpStatus.OK);  // Return the list with 200 status
    }

//    // Update an existing offer by its ID
//    @PutMapping("/{offerId}")
//    public ResponseEntity<OfferDto> updateOffer(@PathVariable int offerId, @RequestBody OfferDto offerDto) {
//        try {
//            OfferDto updatedOffer = offerService.updateOffer(offerId, offerDto);  // Use the service to update the offer
//            return new ResponseEntity<>(updatedOffer, HttpStatus.OK);  // Return the updated offer with 200 status
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if offer not found
//        }
//    }

    // Delete an offer by its ID
    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable int offerId) {
        offerService.deleteOffer(offerId);  // Delete the offer using service
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 No Content for successful deletion
    }
}
