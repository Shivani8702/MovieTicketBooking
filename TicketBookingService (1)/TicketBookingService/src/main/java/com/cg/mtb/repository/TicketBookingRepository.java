package com.cg.mtb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mtb.entity.TicketBookingEntity;

@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBookingEntity, Long> {
  
}
