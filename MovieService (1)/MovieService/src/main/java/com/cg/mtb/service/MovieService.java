package com.cg.mtb.service;

import java.util.List;
import java.util.Set;

import com.cg.mtb.dto.MovieDto;

public interface MovieService {

    Set<MovieDto> getAllMovies();

    MovieDto getMovieById(int movieId);

    MovieDto createMovie(MovieDto movieDTO);

    MovieDto updateMovie(int movieId, MovieDto movieDTO);

    void deleteMovie(int movieId);
}
