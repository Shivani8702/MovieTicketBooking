package com.cg.mtb.service;

import com.cg.mtb.entity.MovieShowEntity;
import com.cg.mtb.dto.MovieShowDto;
import com.cg.mtb.dto.SeatDto;
import com.cg.mtb.repo.MovieShowRapository;
import com.cg.mtb.util.MovieShowUtil;  // Updated import for MovieShowUtil
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class MovieShowServiceImpl implements MovieShowService {

    @Autowired
    private MovieShowRapository movieShowRepository;

    @Override
    @Transactional(readOnly = true)
    public Set<MovieShowDto> getAllMovieShows() {
        List<MovieShowEntity> movieShowEntities = movieShowRepository.findAll();
        return MovieShowUtil.convertEntityListToDtoSet(movieShowEntities);  // Use MovieShowUtil to convert entities to DTOs
    }

    @Override
    @Transactional(readOnly = true)
    public MovieShowDto getMovieShowById(int showId) {
        MovieShowEntity movieShowEntity = movieShowRepository.findById(showId).orElse(null);
        return movieShowEntity != null ? MovieShowUtil.convertToDto(movieShowEntity) : null;  // Convert Entity to DTO using MovieShowUtil
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieShowEntity> getMovieShowsByTheaterId(int theaterId) {
        return movieShowRepository.findByTheatreId(theaterId);  // No changes here, as this returns an entity list
    }

    @Override
    @Transactional
    public MovieShowDto createMovieShow(MovieShowDto movieShowDto) {
        MovieShowEntity movieShowEntity = MovieShowUtil.convertToEntity(movieShowDto);  // Convert DTO to Entity using MovieShowUtil
        MovieShowEntity savedEntity = movieShowRepository.save(movieShowEntity);  // Save the entity
        return MovieShowUtil.convertToDto(savedEntity);  // Convert back to DTO and return
    }

    @Override
    @Transactional
    public MovieShowDto updateMovieShow(int showId, MovieShowDto movieShowDto) {
        if (movieShowRepository.existsById(showId)) {
            MovieShowEntity movieShowEntity = MovieShowUtil.convertToEntity(movieShowDto);  // Convert DTO to Entity using MovieShowUtil
            movieShowEntity.setShowId(showId);  // Set the correct ID for update
            MovieShowEntity updatedEntity = movieShowRepository.save(movieShowEntity);  // Save updated entity
            return MovieShowUtil.convertToDto(updatedEntity);  // Convert back to DTO and return
        }
        return null;  // Return null if not found
    }

    @Override
    @Transactional
    public void deleteMovieShow(int showId) {
        if (movieShowRepository.existsById(showId)) {
            movieShowRepository.deleteById(showId);  // Delete by ID
        }
    }
 

	

    
    
}
