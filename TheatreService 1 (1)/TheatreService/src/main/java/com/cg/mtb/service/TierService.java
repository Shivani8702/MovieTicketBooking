package com.cg.mtb.service;

import java.util.List;
import java.util.Optional;

import com.cg.mtb.dto.TierDto;
import com.cg.mtb.entity.TheatreEntity;
import com.cg.mtb.entity.TierEntity;
import com.cg.mtb.exception.TheatreNotFoundException;
import com.cg.mtb.exception.TierNotFoundException;

public interface TierService {
	List<TierEntity> getAllTiers()throws TierNotFoundException;
	Optional<TierEntity> getTierById(Integer tierId) throws TierNotFoundException;
	List<TierEntity> getTiersByTheatre(int theatreId) throws TierNotFoundException;
	TierEntity addTier(TierEntity tierEntity);
	TierEntity updateTier(int tierId, TierDto tierDto) throws TierNotFoundException, TheatreNotFoundException;
	void deleteTierById(int tierId)throws TierNotFoundException;
	
}
