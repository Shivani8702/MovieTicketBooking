package com.cg.mtb.service;

import com.cg.mtb.dto.TierDto;

import com.cg.mtb.entity.TheatreEntity;
import com.cg.mtb.entity.TierEntity;
import com.cg.mtb.exception.TheatreNotFoundException;
import com.cg.mtb.exception.TierNotFoundException;
import com.cg.mtb.repo.TheatreRepository;
import com.cg.mtb.repo.TierRepository;
import com.cg.mtb.service.TierService;
import com.cg.mtb.util.TierUtil;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TierServiceImpl implements TierService {
//	 @Autowired
//	    private TierService tierService;
	
    @Autowired
    private TierRepository tierRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Override
    public List<TierEntity> getAllTiers() {
        return (List<TierEntity>) tierRepository.findAll();
    }

    @Override
    public Optional<TierEntity> getTierById(Integer tierId) throws TierNotFoundException {
        Optional<TierEntity> tier = tierRepository.findById(tierId);
        if (!tier.isPresent()) {  
            throw new TierNotFoundException("Tier not found with ID: " + tierId);
        }
        return tier;
    }

    @Override
    public List<TierEntity> getTiersByTheatre(int theatreId) throws TierNotFoundException {
        List<TierEntity> tiers = tierRepository.findByTheatre_TheatreId(theatreId);
        if (tiers.isEmpty()) {
            throw new TierNotFoundException("No tiers found for theatre ID: " + theatreId);
        }
        return tiers;
    }

    
   @Override
    public TierEntity addTier(TierEntity tierEntity) {
        return tierRepository.save(tierEntity);
    }
    
   

    @Override
    @Transactional
    public TierEntity updateTier(int tierId, TierDto tierDto) throws TierNotFoundException, TheatreNotFoundException {
        Optional<TierEntity> existingTierOpt = tierRepository.findById(tierId);
        if (existingTierOpt.isEmpty()) {
            throw new TierNotFoundException("Tier with ID " + tierId + " not found");
        }

        TierEntity existingTier = existingTierOpt.get();

        Optional<TheatreEntity> theatreOpt = theatreRepository.findById(tierDto.getTheatreId());
        if (theatreOpt.isEmpty()) {
            throw new TheatreNotFoundException("Theatre with ID " + tierDto.getTheatreId() + " not found");
        }
        existingTier.setTierName(tierDto.getTierName());
        existingTier.setTicketPriceMultiplier(tierDto.getTicketPriceMultiplier());
        existingTier.setTheatre(theatreOpt.get());

        return tierRepository.save(existingTier);
    }
    public void deleteTierById(int tierId) throws TierNotFoundException {
        if (!tierRepository.existsById(tierId)) {
            throw new TierNotFoundException("Tier not found with ID: " + tierId);
        }
        tierRepository.deleteById(tierId);
    }

    

}
