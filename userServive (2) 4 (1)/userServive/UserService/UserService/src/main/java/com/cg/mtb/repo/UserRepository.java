package com.cg.mtb.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mtb.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
	Optional<UserEntity> findByEmail(String email);

}
