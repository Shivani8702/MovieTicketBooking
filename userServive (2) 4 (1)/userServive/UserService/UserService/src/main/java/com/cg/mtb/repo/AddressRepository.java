package com.cg.mtb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mtb.entity.AddressEntity;
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Integer>{

}
