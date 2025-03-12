package com.cg.mtb.repo;

import com.cg.mtb.entity.TheatreEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface TheatreRepository extends CrudRepository<TheatreEntity, Integer> {
	  @Query("SELECT t FROM TheatreEntity t JOIN t.address a WHERE a.city = :cityName")
	    List<TheatreEntity> findByCity(String cityName);
	  Optional<TheatreEntity> findByTheatreName(String theatreName);
	  

}
