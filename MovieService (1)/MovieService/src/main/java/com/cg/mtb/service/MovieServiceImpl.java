package com.cg.mtb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mtb.entity.MovieEntity;
import com.cg.mtb.dto.MovieDto;
import com.cg.mtb.repo.MovieRapository;

import com.cg.mtb.util.MovieServiceUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRapository movieRapository;

    @Override
    @Transactional(readOnly = true)
    public Set<MovieDto> getAllMovies() {
    	List<MovieEntity> movies = movieRapository.findAll(); // Get the list of entities
        return movies.stream()
                     .map(MovieServiceUtil::convertToDto) // Convert each entity to DTO
                     .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDto getMovieById(int movieId) {
        MovieEntity movie = movieRapository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        return MovieServiceUtil.convertToDto(movie);  // Convert to DTO
    }

    @Override
    @Transactional
    public MovieDto createMovie(MovieDto movieDTO) {
        MovieEntity movie = MovieServiceUtil.convertToEntity(movieDTO);

        MovieEntity savedMovie = movieRapository.save(movie);

        return MovieServiceUtil.convertToDto(savedMovie);  // Convert to DTO
    }

    @Override
    @Transactional
    public MovieDto updateMovie(int movieId, MovieDto movieDTO) {
        MovieEntity movie = movieRapository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setMovieName(movieDTO.getMovieName());
        movie.setTicketBasePrice(movieDTO.getTicketBasePrice());

        MovieEntity updatedMovie = movieRapository.save(movie);

        return MovieServiceUtil.convertToDto(updatedMovie);  // Convert to DTO
    }

    @Override
    @Transactional
    public void deleteMovie(int movieId) {
        MovieEntity movie = movieRapository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movieRapository.delete(movie);
    }
}
