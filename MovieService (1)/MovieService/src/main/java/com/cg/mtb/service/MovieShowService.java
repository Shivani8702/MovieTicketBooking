package com.cg.mtb.service;

import com.cg.mtb.dto.MovieShowDto;
import com.cg.mtb.dto.SeatDto;
import com.cg.mtb.entity.MovieShowEntity;

import java.util.List;
import java.util.Set;

public interface MovieShowService {

    // To get all movie shows and return a Set of MovieShowDto
    Set<MovieShowDto> getAllMovieShows();  

    // To get a movie show by show ID and return a MovieShowDto
    MovieShowDto getMovieShowById(int showId);  

    // To get movie shows by theater ID and return a List of MovieShowEntity (no DTO conversion for this method)
   // List<MovieShowEntity> getMovieShowsByTheatreId(int theaterId);  

    // To create a new movie show, takes MovieShowDto and returns MovieShowDto
    MovieShowDto createMovieShow(MovieShowDto movieShowDto);  

    // To update a movie show, takes MovieShowDto and returns updated MovieShowDto
    MovieShowDto updateMovieShow(int showId, MovieShowDto movieShowDto);  

    // To delete a movie show, takes showId and performs deletion (returns nothing)
    void deleteMovieShow(int showId);

	List<MovieShowEntity> getMovieShowsByTheaterId(int theaterId);

	

	//List<MovieShowEntity> getMovieShowsByTheaterId(int theaterId);

     
}
