package com.cg.mtb.util;

import com.cg.mtb.dto.MovieDto;
import com.cg.mtb.entity.MovieEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieServiceUtil {

    // Convert MovieEntity to MovieDto
    public static MovieDto convertToDto(MovieEntity movie) {
        return new MovieDto(movie.getMovieId(), movie.getMovieName(), movie.getTicketBasePrice());
    }

    // Convert MovieDto to MovieEntity
    public static MovieEntity convertToEntity(MovieDto movieDTO) {
        MovieEntity movie = new MovieEntity();
        movie.setMovieName(movieDTO.getMovieName());
        movie.setTicketBasePrice(movieDTO.getTicketBasePrice());
        return movie;
    }

    // Convert List<MovieEntity> to Set<MovieDto>
    public static Set<MovieDto> convertEntityListToDtoSet(Set<MovieEntity> movies) {
        return movies.stream()
                .map(MovieServiceUtil::convertToDto)
                .collect(Collectors.toSet());
    }
}

