package com.cg.mtb.repo;
 
import com.cg.mtb.entity.SeatEntity;
import com.cg.mtb.entity.TierEntity;
 
import jakarta.transaction.Transactional;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Integer> {
	Optional<SeatEntity> findById(int seatId);
	//List<SeatEntity> findByTierInAndBooked(List<TierEntity> tiers,  boolean booked);
	List<SeatEntity> findByTierIn(List<TierEntity> tiers);
	List<SeatEntity> findByTier_TierId(int tierId);
	void deleteById(int seatId);
	
 
//	@Modifying
//    @Transactional
//    @Query("UPDATE SeatEntity s SET s.booked = false WHERE s.seatId = :seatId")
//    int cancelReservation(@Param("seatId") int seatId); 
}