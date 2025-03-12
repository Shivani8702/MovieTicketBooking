//package com.cg.mtb.util;
//
//import com.cg.mtb.dto.OfferDto;
//import com.cg.mtb.entity.OfferEntity;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//public class OfferUtil {
//
//    // Convert OfferEntity to OfferDto
//    public static OfferDto convertToDto(OfferEntity offerEntity) {
//        if (offerEntity == null) {
//            return null;
//        }
//        
//        // Convert OfferEntity to OfferDto
//        OfferDto offerDto = new OfferDto();
//        offerDto.setOfferId(offerEntity.getOfferId());
//        offerDto.setOfferDetails(offerEntity.getOfferDetails());
//        
//        // Convert MovieShowEntity to MovieShowDto
//        // You would need to have a MovieShowDto for this conversion
//        offerDto.setMovieShow(offerEntity.getMovieShow()); // Assuming MovieShowDto can be set like this directly.
//
//        return offerDto;
//    }
//
//    // Convert OfferDto to OfferEntity
//    public static OfferEntity convertToEntity(OfferDto offerDto) {
//        if (offerDto == null) {
//            return null;
//        }
//        
//        // Convert OfferDto to OfferEntity
//        OfferEntity offerEntity = new OfferEntity();
//        offerEntity.setOfferId(offerDto.getOfferId());
//        offerEntity.setOfferDetails(offerDto.getOfferDetails());
//        
//        // Convert MovieShowDto to MovieShowEntity
//        // Assuming you have a way to convert MovieShowDto to MovieShowEntity
//        offerEntity.setMovieShow(convertMovieShowToEntity(offerDto.getMovieShow()));
//
//        return offerEntity;
//    }
//
//    // Convert List<OfferEntity> to Set<OfferDto>
//    public static Set<OfferDto> convertEntityListToDtoSet(List<OfferEntity> offerEntities) {
//        if (offerEntities == null) {
//            return null;
//        }
//        
//        // Convert List<OfferEntity> to Set<OfferDto>
//        return offerEntities.stream()
//                            .map(OfferUtil::convertToDto)  // Convert each entity to DTO
//                            .collect(Collectors.toSet());
//    }
//}
package com.cg.mtb.util;

import com.cg.mtb.dto.MovieShowDto;
import com.cg.mtb.dto.OfferDto;
import com.cg.mtb.entity.MovieShowEntity;
import com.cg.mtb.entity.OfferEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OfferUtil {

    // Convert OfferEntity to OfferDto
    public static OfferDto convertToDto(OfferEntity offerEntity) {
        if (offerEntity == null) {
            return null;
        }
        
        // Convert OfferEntity to OfferDto
        OfferDto offerDto = new OfferDto();
        offerDto.setOfferId(offerEntity.getOfferId());
        offerDto.setOfferDetails(offerEntity.getOfferDetails());
        
        // Convert MovieShowEntity to MovieShowDto
        offerDto.setMovieShow(convertMovieShowToDto(offerEntity.getMovieShow()));

        return offerDto;
    }

    // Convert OfferDto to OfferEntity
    public static OfferEntity convertToEntity(OfferDto offerDto) {
        if (offerDto == null) {
            return null;
        }
        
        // Convert OfferDto to OfferEntity
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setOfferId(offerDto.getOfferId());
        offerEntity.setOfferDetails(offerDto.getOfferDetails());
        
        // Convert MovieShowDto to MovieShowEntity
        offerEntity.setMovieShow(convertMovieShowToEntity(offerDto.getMovieShow()));

        return offerEntity;
    }

    // Convert List<OfferEntity> to Set<OfferDto>
    public static Set<OfferDto> convertEntityListToDtoSet(List<OfferEntity> offerEntities) {
        if (offerEntities == null) {
            return null;
        }
        
        // Convert List<OfferEntity> to Set<OfferDto>
        return offerEntities.stream()
                            .map(OfferUtil::convertToDto)  // Convert each entity to DTO
                            .collect(Collectors.toSet());
    }

    // Helper method to convert MovieShowEntity to MovieShowDto
    public static MovieShowDto convertMovieShowToDto(MovieShowEntity movieShowEntity) {
        if (movieShowEntity == null) {
            return null;
        }

        MovieShowDto movieShowDto = new MovieShowDto();
        movieShowDto.setShowId(movieShowEntity.getShowId());
        movieShowDto.setMovieId(movieShowEntity.getMovie().getMovieId());  // Assuming Movie has a movieId field
//        movieShowDto.setTheaterId(movieShowEntity.getTheater().getTheaterId());  // Assuming Theater has a theaterId field
        movieShowDto.setShowDatetime(movieShowEntity.getShowDatetime());

        return movieShowDto;
    }

    // Helper method to convert MovieShowDto to MovieShowEntity
    public static MovieShowEntity convertMovieShowToEntity(MovieShowDto movieShowDto) {
        if (movieShowDto == null) {
            return null;
        }

        MovieShowEntity movieShowEntity = new MovieShowEntity();
        movieShowEntity.setShowId(movieShowDto.getShowId());
        // Assume you need to populate movie and theater, e.g., by looking them up by ID
        // movieShowEntity.setMovie(...);
        // movieShowEntity.setTheater(...);
        movieShowEntity.setShowDatetime(movieShowDto.getShowDatetime());

        return movieShowEntity;
    }
}

