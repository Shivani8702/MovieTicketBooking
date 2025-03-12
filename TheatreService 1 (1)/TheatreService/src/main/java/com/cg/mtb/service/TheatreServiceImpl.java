package com.cg.mtb.service;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.dto.TheatreDto;
import com.cg.mtb.entity.AddressEntity;

import com.cg.mtb.entity.TheatreEntity;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.TheatreNotFoundException;

import com.cg.mtb.repo.AddressRepository;

import com.cg.mtb.repo.TheatreRepository;

import com.cg.mtb.util.AddressUtil;
import com.cg.mtb.util.TheatreUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


@Service
public class TheatreServiceImpl implements TheatreService {

    private final RestTemplate restTemplate;
  //  @Autowired
  //  private MovieFeignClient movieFeignClient;
    
    @Autowired
    TheatreRepository theatreRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
   

    public TheatreServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Override
//    public List<TheatreDto> getAllTheatres() throws TheatreNotFoundException {
//        String url = "http://localhost:8011/api/theatres"; // Replace with actual Theatre Service URL
//        try {
//            TheatreDto[] theatres = restTemplate.getForObject(url, TheatreDto[].class);
//            if (theatres == null || theatres.length == 0) {
//               throw new TheatreNotFoundException("No theatres found");
//           }
//           return Arrays.asList(theatres);
//        } catch (HttpClientErrorException e) {
//            throw new TheatreNotFoundException("Error fetching theatres: " + e.getMessage());
//        }
//    }
    
        	 @Override
        	    public List<TheatreDto> getAllTheatres() throws TheatreNotFoundException {
        	        List<TheatreEntity> theatreEntities = (List<TheatreEntity>) theatreRepository.findAll();

        	        if (theatreEntities.isEmpty()) {
        	            throw new TheatreNotFoundException("No theatres found");
        	        }

        	        List<TheatreDto> theatreDtos = new ArrayList<>();
        	        for (TheatreEntity theatre : theatreEntities) {
        	            theatreDtos.add(TheatreUtil.toDto(theatre));
        	        }

        	        return theatreDtos;
        	    }

	@Override
	
    public TheatreDto getTheatreById(int theatreId) throws TheatreNotFoundException {
        Optional<TheatreEntity> optionalTheatre = theatreRepository.findById(theatreId);
        if (optionalTheatre.isEmpty()) {
            throw new TheatreNotFoundException("Theatre with ID " + theatreId + " not found");
        }
        return TheatreUtil.toDto(optionalTheatre.get());
    }

	@Override
	public AddressDto getTheatreLocation(int theatreId) throws TheatreNotFoundException {
        TheatreEntity theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new TheatreNotFoundException("Theatre not found with ID: " + theatreId));

        return AddressUtil.toDto(theatre.getAddress());
    }

	
	
	@Override
	public List<TheatreDto> getTheatresByCity(String cityName) throws TheatreNotFoundException {
	    List<TheatreEntity> theatres = theatreRepository.findByCity(cityName);
	    
	    if (theatres.isEmpty()) {
	        throw new TheatreNotFoundException("No theatres found in city: " + cityName);
	    }

	    return TheatreUtil.toDtoList(theatres); 
	}
	
	 @Override
	    public TheatreDto addTheatre(TheatreDto theatreDto) throws AddressNotFoundException {

	        Optional<AddressEntity> optionalAddress = addressRepository.findById(theatreDto.getAddressId());
	        if (!optionalAddress.isPresent()) {
	            throw new AddressNotFoundException("Address with ID " + theatreDto.getAddressId() + " not found.");
	        }

	        TheatreEntity theatreEntity = TheatreUtil.toEntity(theatreDto);
	        theatreEntity.setAddress(optionalAddress.get()); // Set foreign key

	        TheatreEntity savedTheatre = theatreRepository.save(theatreEntity);
	        return TheatreUtil.toDto(savedTheatre);
	    }
	 @Override
	 public void deleteTheatre(int theatreId) throws TheatreNotFoundException {
	  
	     TheatreEntity theatreEntity = theatreRepository.findById(theatreId)
	             .orElseThrow(() -> new TheatreNotFoundException("Theatre with ID " + theatreId + " not found."));

	     theatreRepository.delete(theatreEntity);
	 }
	 @Override
	 public TheatreDto updateTheatre(int theatreId, TheatreDto theatreDto) throws TheatreNotFoundException {
	     TheatreEntity theatre = theatreRepository.findById(theatreId)
	             .orElseThrow(() -> new TheatreNotFoundException("Theatre with ID " + theatreId + " not found"));

	     theatre.setTheatreName(theatreDto.getTheatreName());
	     theatre.setCapacity(theatreDto.getCapacity());  
	     theatre.setScreen(theatreDto.getScreen());  

	     TheatreEntity updatedTheatre = theatreRepository.save(theatre); 
	     return TheatreUtil.toDto(updatedTheatre); // 
	 }
	//Get the seating capacity of a specific theatre.
		 @Override
		    public int getTheatreCapacity(int theatreId) throws TheatreNotFoundException {
		        TheatreEntity theatre = theatreRepository.findById(theatreId)
		                .orElseThrow(() -> new TheatreNotFoundException("Theatre with ID " + theatreId + " not found"));
		        return theatre.getCapacity(); // Returning the seating capacity
		    }
		
		 //Get a list of nearby theatres based on location.
		 @Override
		    public List<TheatreDto> getNearbyTheatres(int theatreId) throws TheatreNotFoundException {
		        // Retrieve the theatre by ID
		        Optional<TheatreEntity> theatreOptional = theatreRepository.findById(theatreId);
		        if (theatreOptional.isEmpty()) {
		            throw new TheatreNotFoundException("Theatre with ID " + theatreId + " not found");
		        }
		        TheatreEntity theatre = theatreOptional.get();
	 
		        // Retrieve the address of the given theatre
		        AddressDto addressDto = AddressUtil.toDto(theatre.getAddress());
	 
		        // Example simple logic: Only consider theatres with the same city in the address
		        List<TheatreEntity> allTheatres = (List<TheatreEntity>) theatreRepository.findAll();
	 
		        List<TheatreDto> nearbyTheatres = new ArrayList<>();
		        for (TheatreEntity nearbyTheatre : allTheatres) {
		            // Example simple logic: Only consider theatres with the same city in the address
		            if (nearbyTheatre.getTheatreId() != theatreId && nearbyTheatre.getAddress().getCity().equals(addressDto.getCity())) {
		                nearbyTheatres.add(TheatreUtil.toDto(nearbyTheatre));
		            }
		        }
	 
	 
		        if (nearbyTheatres.isEmpty()) {
		            throw new TheatreNotFoundException("No nearby theatres found");
		        }
	 
		        return nearbyTheatres;
		    }
		
		 // Search a theatre by name.
		 @Override
		 public List<TheatreDto> searchTheatreByName(String theatreName) throws TheatreNotFoundException {
		     // Fetch all theatres from the database
		     List<TheatreEntity> allTheatres = (List<TheatreEntity>) theatreRepository.findAll();
	 
		     // Filter theatres by name
		     List<TheatreDto> theatreDtos = new ArrayList<>();
		     for (TheatreEntity theatre : allTheatres) {
		         // Check if the theatre name matches the search query (case insensitive)
		         if (theatre.getTheatreName().toLowerCase().contains(theatreName.toLowerCase())) {
		             theatreDtos.add(TheatreUtil.toDto(theatre));
		         }
		     }
	 
		     if (theatreDtos.isEmpty()) {
		         throw new TheatreNotFoundException("No theatres found with the name: " + theatreName);
		     }
	 
		     return theatreDtos;
		 }
		
		 //Get a list of theatres in a specific state.
		
		 @Override
		 public List<TheatreDto> getTheatresByState(String state) throws TheatreNotFoundException {
		     // Fetch all theatres
		     List<TheatreEntity> theatreEntities = (List<TheatreEntity>) theatreRepository.findAll();
	 
		     // Filter theatres by state
		     List<TheatreDto> filteredTheatres = new ArrayList<>();
		     for (TheatreEntity theatre : theatreEntities) {
		         if (theatre.getAddress().getState().equalsIgnoreCase(state)) {
		             filteredTheatres.add(TheatreUtil.toDto(theatre));
		         }
		     }
	 
		     if (filteredTheatres.isEmpty()) {
		         throw new TheatreNotFoundException("No theatres found in the state: " + state);
		     }
	 
		     return filteredTheatres;
		 }
		 @Override
		 public String getScreenByTheatreId(int theatreId) throws TheatreNotFoundException {
			    TheatreEntity theatre = theatreRepository.findById(theatreId)
			            .orElseThrow(() -> new TheatreNotFoundException("Theatre with ID " + theatreId + " not found"));

			    System.out.println("Fetched Theatre: " + theatre);
			    System.out.println("Screen Type from DB: " + theatre.getScreen());

			    if (theatre.getScreen() == null || theatre.getScreen().isEmpty()) {
			        throw new TheatreNotFoundException("Screen not found for theatre ID " + theatreId);
			    }

			    return theatre.getScreen();
			}
//		 @Override
//		 public List<MovieShowDto> getMovieShowsByTheatreId(int theatreId) throws TheatreNotFoundException {
//		     TheatreEntity theatre = theatreRepository.findById(theatreId)
//		         .orElseThrow(() -> new TheatreNotFoundException("Theatre not found with ID: " + theatreId));
//
//		     // Assuming there is a repository for fetching movie shows
//		     List<MovieShowEntity> movieShows = movieShowRepo.findByTheatre(theatre);
//
//		     return movieShows.stream()
//		         .map(show -> new MovieShowDto(show.getId(), show.getMovie().getTitle(), show.getStartTime(), show.getEndTime()))
//		         .collect(Collectors.toList());
//		 }
//
//		 
		 @Override
		 public TheatreDto getTheatreByName(String theatreName) throws TheatreNotFoundException {
		     TheatreEntity theatre = theatreRepository.findByTheatreName(theatreName)
		             .orElseThrow(() -> new TheatreNotFoundException("Theatre not found with name: " + theatreName));

		     return new TheatreDto(
		             theatre.getTheatreId(),
		             theatre.getAddress().getAddressId(), // Ensure AddressEntity has getAddressId()
		             theatre.getTheatreName(),
		             theatre.getCapacity(),
		             theatre.getScreen()
		     );
		 }
		
//		 @Override
//		    public List<MovieShowDto> getShowsByTheatreId(int theatreId) {
//		        return movieFeignClient.getMovieShowsByTheatreId(theatreId);
//		    }
//		

	}


