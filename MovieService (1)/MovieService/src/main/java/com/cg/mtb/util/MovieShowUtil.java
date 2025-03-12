package com.cg.mtb.util;

import com.cg.mtb.dto.MovieShowDto;
import com.cg.mtb.entity.MovieShowEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieShowUtil {

    // Convert MovieShowEntity to MovieShowDto
    public static MovieShowDto convertToDto(MovieShowEntity movieShowEntity) {
        return new MovieShowDto(
                movieShowEntity.getShowId(),
                movieShowEntity.getMovie().getMovieId(),
                movieShowEntity.getTheatreId(),
                movieShowEntity.getShowDatetime()
                //movieShowEntity.getTicketPrice()
        );
    }

    // Convert MovieShowDto to MovieShowEntity
    public static MovieShowEntity convertToEntity(MovieShowDto movieShowDto) {
        MovieShowEntity movieShowEntity = new MovieShowEntity();
        movieShowEntity.setShowDatetime(movieShowDto.getShowDatetime());
        //movieShowEntity.setTicketPrice(movieShowDto.getTicketPrice());
        return movieShowEntity;
    }

    // Convert List<MovieShowEntity> to Set<MovieShowDto>
    public static Set<MovieShowDto> convertEntityListToDtoSet(List<MovieShowEntity> movieShowEntities) {
        return movieShowEntities.stream()
                .map(MovieShowUtil::convertToDto)
                .collect(Collectors.toSet());
    }
}

