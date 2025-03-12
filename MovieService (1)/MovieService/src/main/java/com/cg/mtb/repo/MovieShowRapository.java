package com.cg.mtb.repo;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mtb.entity.MovieShowEntity;


@Repository
public interface MovieShowRapository extends JpaRepository<MovieShowEntity, Integer> {

	List<MovieShowEntity> findByTheatreId(int theatreId);
//	List<MovieShowEntity> findByMovie_MovieId(int movieId);
	
}
