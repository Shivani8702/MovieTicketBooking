package com.cg.mtb.ctrl;

import com.cg.mtb.dto.MovieShowDto;  // Use MovieShowDto
import com.cg.mtb.dto.SeatDto;
import com.cg.mtb.entity.MovieShowEntity;
import com.cg.mtb.service.MovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/movie-shows")  // Base URL for movie show-related endpoints
public class MovieShowCtrl {

    @Autowired
    private MovieShowService movieShowService;

    // Endpoint to fetch all movie shows
    @GetMapping
    public ResponseEntity<Set<MovieShowDto>> getAllMovieShows() {
        Set<MovieShowDto> movieShows = movieShowService.getAllMovieShows();
        return new ResponseEntity<>(movieShows, HttpStatus.OK);  // Return Set<MovieShowDto>
    }

    // Endpoint to fetch a movie show by its ID
    @GetMapping("/{showId}")
    public ResponseEntity<MovieShowDto> getMovieShowById(@PathVariable int showId) {
        MovieShowDto movieShow = movieShowService.getMovieShowById(showId);
        if (movieShow != null) {
            return new ResponseEntity<>(movieShow, HttpStatus.OK);  // Return MovieShowDto
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
    }

    // Endpoint to fetch movie shows by theater ID
    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<MovieShowEntity>> getMovieShowsByTheaterId(@PathVariable int theaterId) {
        List<MovieShowEntity> movieShows = movieShowService.getMovieShowsByTheaterId(theaterId);
        return new ResponseEntity<>(movieShows, HttpStatus.OK);  // Return List<MovieShowDto>
    }

    // Endpoint to create a new movie show
    @PostMapping
    public ResponseEntity<MovieShowDto> createMovieShow(@RequestBody MovieShowDto movieShowDto) {
        MovieShowDto createdMovieShow = movieShowService.createMovieShow(movieShowDto);
        return new ResponseEntity<>(createdMovieShow, HttpStatus.CREATED);  // 201 Created
    }

    // Endpoint to update an existing movie show
    @PutMapping("/{showId}")
    public ResponseEntity<MovieShowDto> updateMovieShow(
            @PathVariable int showId, @RequestBody MovieShowDto movieShowDto) {
        MovieShowDto updatedMovieShow = movieShowService.updateMovieShow(showId, movieShowDto);
        if (updatedMovieShow != null) {
            return new ResponseEntity<>(updatedMovieShow, HttpStatus.OK);  // 200 OK
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 if not found
    }

    // Endpoint to delete a movie show by its ID
    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteMovieShow(@PathVariable int showId) {
        movieShowService.deleteMovieShow(showId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
    }
    
 // Endpoint to fetch theaters showing a specific movie
//    @GetMapping("/movie/{movieId}")
//    public ResponseEntity<List<Integer>> getTheaterIdsByMovie(@PathVariable int movieId) {
//        List<Integer> theaterIds = movieShowService.getTheaterIdsByMovie(movieId);
//        return new ResponseEntity<>(theaterIds, HttpStatus.OK);
//    }
   
}
