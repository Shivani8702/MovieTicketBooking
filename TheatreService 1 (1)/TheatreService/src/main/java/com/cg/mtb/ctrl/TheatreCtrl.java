package com.cg.mtb.ctrl;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.dto.MovieShowDto;
import com.cg.mtb.dto.TheatreDto;
import com.cg.mtb.entity.SeatEntity;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.TheatreNotFoundException;
import com.cg.mtb.service.SeatService;
import com.cg.mtb.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/api/theatres")
public class TheatreCtrl {
    private final TheatreService theatreService;
   private final RestTemplate restTemplate;

    @Autowired
    public TheatreCtrl(TheatreService theatreService, RestTemplate restTemplate) {
        this.theatreService = theatreService;
        this.restTemplate = restTemplate;
    }

    // Get all theatres
    @GetMapping
    public ResponseEntity<List<TheatreDto>> getAllTheatres() throws TheatreNotFoundException {
        List<TheatreDto> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatres);
    }

    // Get theatre by ID
    @GetMapping("/{theatreId}")
    public ResponseEntity<TheatreDto> getTheatreById(@PathVariable("theatreId") int theatreId)
            throws TheatreNotFoundException {
        TheatreDto theatreDto = theatreService.getTheatreById(theatreId);
        return ResponseEntity.ok(theatreDto);
    }

    // Get location of a specific theatre
    @GetMapping("/{theatreId}/location")
    public ResponseEntity<AddressDto> getTheatreLocation(@PathVariable("theatreId") int theatreId)
            throws TheatreNotFoundException {
        AddressDto addressDto = theatreService.getTheatreLocation(theatreId);
        return ResponseEntity.ok(addressDto);
    }

    // Get theatres in a specific city
    @GetMapping("/location")
    public ResponseEntity<List<TheatreDto>> getTheatresByCity(@RequestParam String city)
            throws TheatreNotFoundException {
        List<TheatreDto> theatres = theatreService.getTheatresByCity(city);
        return ResponseEntity.ok(theatres);
    }

    // Add a new theatre
    @PostMapping
    public ResponseEntity<TheatreDto> addTheatre(@RequestBody TheatreDto theatreDto) throws AddressNotFoundException {
        TheatreDto savedTheatre = theatreService.addTheatre(theatreDto);
        return new ResponseEntity<>(savedTheatre, HttpStatus.CREATED);
    }

    // Delete a theatre
    @DeleteMapping("/{theatreId}")
    public ResponseEntity<String> deleteTheatre(@PathVariable int theatreId) {
        try {
            theatreService.deleteTheatre(theatreId);
            return ResponseEntity.ok("Theatre with ID " + theatreId + " deleted successfully.");
        } catch (TheatreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Update a theatre
    @PutMapping("/{theatreId}")
    public ResponseEntity<?> updateTheatre(@PathVariable int theatreId, @RequestBody TheatreDto theatreDto) {
        try {
            TheatreDto updatedTheatre = theatreService.updateTheatre(theatreId, theatreDto);
            return ResponseEntity.ok(updatedTheatre);
        } catch (TheatreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating theatre");
        }
    }

    // Get the seating capacity of a specific theatre
    @GetMapping("/{theatreId}/capacity")
    public ResponseEntity<?> getTheatreCapacity(@PathVariable("theatreId") int theatreId) {
        try {
            int capacity = theatreService.getTheatreCapacity(theatreId);
            return ResponseEntity.ok(capacity);
        } catch (TheatreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get a list of nearby theatres based on location
    @GetMapping("/{theatreId}/nearby")
    public ResponseEntity<?> getNearbyTheatres(@PathVariable("theatreId") int theatreId) {
        try {
            List<TheatreDto> nearbyTheatres = theatreService.getNearbyTheatres(theatreId);
            return ResponseEntity.ok(nearbyTheatres);
        } catch (TheatreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Search a theatre by name
    @GetMapping("/search")
    public ResponseEntity<?> searchTheatreByName(@RequestParam("name") String theatreName) {
        try {
            List<TheatreDto> theatres = theatreService.searchTheatreByName(theatreName);
            return ResponseEntity.ok(theatres);
        } catch (TheatreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get a list of theatres in a specific state
    @GetMapping("/filter")
    public ResponseEntity<?> getTheatresByState(@RequestParam("state") String state) {
        try {
            List<TheatreDto> theatres = theatreService.getTheatresByState(state);
            return ResponseEntity.ok(theatres);
        } catch (TheatreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get screens of a specific theatre
    @GetMapping("/{theatreId}/screen")
    public ResponseEntity<String> getScreen(@PathVariable("theatreId") int theatreId) throws TheatreNotFoundException {
        String screen = theatreService.getScreenByTheatreId(theatreId);

        if (screen == null || screen.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No screen found for theatre ID " + theatreId);
        }
        return ResponseEntity.ok(screen);
    }
//    @GetMapping("/movie/{movieId}")
//    public ResponseEntity<?> getTheatresByMovie(@PathVariable int movieId) {
//        try {
//            String movieShowServiceUrl = "http://MOVIE-SHOW-SERVICE/api/movie-shows/movie/" + movieId;
//            ResponseEntity<List<Integer>> response = restTemplate.getForEntity(movieShowServiceUrl, (Class<List<Integer>>) (Object) List.class);
//
//            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//                List<Integer> theaterIds = response.getBody();
//                Set<TheatreDto> theatres = theaterIds.stream()
//                        .map(id -> {
//                            try {
//                                return theatreService.getTheatreById(id);
//                            } catch (TheatreNotFoundException e) {
//                                return null;
//                            }
//                        })
//                        .filter(theatre -> theatre != null)
//                        .collect(Collectors.toSet());
//
//                return new ResponseEntity<>(theatres, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving theatres for movie ID " + movieId);
//        }
//    }
    //get thetare by name
    @GetMapping("/name/{theatreName}")
    public ResponseEntity<TheatreDto> getTheatreByName(@PathVariable String theatreName) throws TheatreNotFoundException {
        TheatreDto theatre = theatreService.getTheatreByName(theatreName);
        return ResponseEntity.ok(theatre);
    }
//    @GetMapping("/{theatreId}/shows")
//    public List<MovieShowDto> getMovieShows(@PathVariable int theatreId) {
//        return theatreService.getShowsByTheatreId(theatreId);
//    }
    
    
  
    
}