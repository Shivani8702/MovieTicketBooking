package com.cg.mtb.ctrl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.mtb.dto.TheatreDto;
import com.cg.mtb.dto.TierDto;
import com.cg.mtb.entity.TheatreEntity;
import com.cg.mtb.entity.TierEntity;
import com.cg.mtb.exception.TheatreNotFoundException;
import com.cg.mtb.exception.TierNotFoundException;
import com.cg.mtb.service.TheatreService;
import com.cg.mtb.service.TierService;
import com.cg.mtb.util.TheatreUtil;
import com.cg.mtb.util.TierUtil;



@RestController
@RequestMapping("/api/tiers")
public class TierCtrl {
	private final RestTemplate restTemplate;

    @Autowired
    private final TierService tierService;

    @Autowired
    private final TheatreService theatreService;

   

    public TierCtrl(RestTemplate restTemplate, TierService tierService, TheatreService theatreService) {
		super();
		this.restTemplate = restTemplate;
		this.tierService = tierService;
		this.theatreService = theatreService;
	}

	@GetMapping
    public List<TierEntity> getAllTiers() throws TierNotFoundException {
        return tierService.getAllTiers();
    }

    @GetMapping("/{tierId}")
    public Optional<TierEntity> getTierById(@PathVariable Integer tierId) throws TierNotFoundException {
        return tierService.getTierById(tierId);
    }

    @GetMapping("/theatre/{theatreId}")
    public List<TierEntity> getTiersByTheatre(@PathVariable int theatreId) throws TierNotFoundException {
        return tierService.getTiersByTheatre(theatreId);
    }
    @PostMapping("/{theatreId}")
    public ResponseEntity<TierDto> addTier(@RequestBody TierDto tierDto, @PathVariable("theatreId") int theatreId) 
            throws TheatreNotFoundException {
        
        // Fetch TheatreDto (assuming getTheatreById() returns TheatreDto)
        TheatreDto theatreDto = theatreService.getTheatreById(theatreId);

        // Validate ticket price multiplier
        if (tierDto.getTicketPriceMultiplier() == null) {
            return ResponseEntity.badRequest().build();  // Better: Return error message
        }

        // Convert DTO to Entity and set theatre reference
        TheatreEntity theatreEntity = TheatreUtil.toEntity(theatreDto);
        TierEntity tierEntity = TierUtil.toEntity(tierDto, theatreEntity);
        
        // Save Tier
        TierEntity savedTier = tierService.addTier(tierEntity);

        // Convert saved TierEntity to DTO and return
        return new ResponseEntity<>(TierUtil.toDto(savedTier), HttpStatus.CREATED);
    }





    @PutMapping("/{tierId}")
    public ResponseEntity<TierEntity> updateTier(@PathVariable int tierId, @RequestBody TierDto tierDto) {
        try {
            TierEntity updatedTier = tierService.updateTier(tierId, tierDto);
            return new ResponseEntity<>(updatedTier, HttpStatus.OK);
        } catch (TierNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (TheatreNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{tierId}")
    public ResponseEntity<String> deleteTier(@PathVariable int tierId) throws TierNotFoundException {
        tierService.deleteTierById(tierId);
        return ResponseEntity.ok("Tier deleted successfully");
    }

}





