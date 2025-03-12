package com.cg.mtb.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mtb.dto.OfferDto;
import com.cg.mtb.entity.OfferEntity;


@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {

	//List<OfferEntity> findByShowId(int showId);
}
