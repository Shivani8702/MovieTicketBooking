package com.cg.mtb.service;

import com.cg.mtb.entity.MovieShowEntity;
import com.cg.mtb.entity.OfferEntity;
import com.cg.mtb.dto.OfferDto;
import com.cg.mtb.repo.OfferRepository;
import com.cg.mtb.util.OfferUtil;  // Import the utility class for conversion

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDto createOffer(OfferDto offerDto) {
        // Convert OfferDto to OfferEntity using the utility method
        OfferEntity offerEntity = OfferUtil.convertToEntity(offerDto);
        OfferEntity savedOffer = offerRepository.save(offerEntity);
        
        // Convert the saved OfferEntity back to OfferDto
        return OfferUtil.convertToDto(savedOffer);
    }

    @Override
    public Optional<OfferDto> getOfferById(int offerId) {
        Optional<OfferEntity> offerEntity = offerRepository.findById(offerId);
        
        // Convert OfferEntity to OfferDto using the utility method if present
        return offerEntity.map(OfferUtil::convertToDto);
    }

    @Override
    public Set<OfferDto> getAllOffers() {
        List<OfferEntity> offerEntities = offerRepository.findAll();
        
        // Convert List<OfferEntity> to Set<OfferDto> using the utility method
        return OfferUtil.convertEntityListToDtoSet(offerEntities);
    }

//    @Override
//    public OfferDto updateOffer(int offerId, OfferDto offerDto) {
//        Optional<OfferEntity> existingOffer = offerRepository.findById(offerId);
//        
//        if (existingOffer.isPresent()) {
//            OfferEntity updatedOfferEntity = existingOffer.get();
//            updatedOfferEntity.setMovieShow(offerDto.getMovieShow());
//            updatedOfferEntity.setOfferDetails(offerDto.getOfferDetails());
//
//            // Save and return the updated OfferDto
//            OfferEntity savedOffer = offerRepository.save(updatedOfferEntity);
//            return OfferUtil.convertToDto(savedOffer);
//        } else {
//            throw new RuntimeException("Offer not found with ID: " + offerId);
//        }
//    }
    
//    @Override
//    public OfferDto updateOffer(int offerId, OfferDto offerDto) {
//        Optional<OfferEntity> existingOffer = offerRepository.findById(offerId);
//        
//        if (existingOffer.isPresent()) {
//            OfferEntity updatedOfferEntity = existingOffer.get();
//            
//            // Convert the MovieShowDto to MovieShowEntity
//            MovieShowEntity updatedMovieShowEntity = OfferUtil.convertMovieShowToEntity(offerDto.getMovieShow());
//            
//            // Set the updated MovieShowEntity and OfferDetails
//            updatedOfferEntity.setMovieShow(updatedMovieShowEntity);
//            updatedOfferEntity.setOfferDetails(offerDto.getOfferDetails());
//
//            // Save and return the updated OfferDto
//            OfferEntity savedOffer = offerRepository.save(updatedOfferEntity);
//            return OfferUtil.convertToDto(savedOffer);
//        } else {
//            throw new RuntimeException("Offer not found with ID: " + offerId);
//        }
//    }
//    @Transactional
//    public OfferDto updateOffer(int offerId, OfferDto offerDto) {
//        Optional<OfferEntity> existingOffer = offerRepository.findById(offerId);
//        
//        if (existingOffer.isPresent()) {
//            OfferEntity updatedOfferEntity = existingOffer.get();
//            
//            // Convert the MovieShowDto to MovieShowEntity
//            MovieShowEntity updatedMovieShowEntity = OfferUtil.convertMovieShowToEntity(offerDto.getMovieShow());
//            
//            // Set the updated MovieShowEntity and OfferDetails
//            updatedOfferEntity.setMovieShow(updatedMovieShowEntity);
//            updatedOfferEntity.setOfferDetails(offerDto.getOfferDetails());
//
//            // Save and return the updated OfferDto
//            OfferEntity savedOffer = offerRepository.save(updatedOfferEntity);
//            return OfferUtil.convertToDto(savedOffer);
//        } else {
//            throw new RuntimeException("Offer not found with ID: " + offerId);
//        }
//    }

    @Override
    public void deleteOffer(int offerId) {
        offerRepository.deleteById(offerId);
    }
}
