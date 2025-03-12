package com.cg.mtb.ctrl;

import com.cg.mtb.dto.MovieDto;
import com.cg.mtb.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieCtrl {

    @Autowired
    private MovieServiceImpl movieService;

    // Get all movies
    @GetMapping
    public ResponseEntity<Set<MovieDto>> getAllMovies() {
        Set<MovieDto> movies = movieService.getAllMovies(); // Return Set<MovieDto>
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    // Get a movie by ID
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable int movieId) {
        MovieDto movie = movieService.getMovieById(movieId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // Create a new movie
    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        MovieDto createdMovie = movieService.createMovie(movieDto);
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    // Update an existing movie
    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable int movieId, @RequestBody MovieDto movieDto) {
        MovieDto updatedMovie = movieService.updateMovie(movieId, movieDto);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    // Delete a movie by ID
    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
