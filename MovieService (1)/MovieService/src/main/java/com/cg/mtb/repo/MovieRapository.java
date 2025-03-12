package com.cg.mtb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mtb.dto.MovieDto;
import com.cg.mtb.entity.MovieEntity;


@Repository
public interface MovieRapository extends JpaRepository<MovieEntity, Integer> {
}
