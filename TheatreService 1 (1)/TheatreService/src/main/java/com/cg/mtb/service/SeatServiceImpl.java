package com.cg.mtb.service;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
 
import com.cg.mtb.dto.SeatDto;
import com.cg.mtb.entity.SeatEntity;
import com.cg.mtb.entity.TierEntity;
import com.cg.mtb.exception.SeatNotFoundException;
import com.cg.mtb.exception.TierNotFoundException;
import com.cg.mtb.repo.AddressRepository;
import com.cg.mtb.repo.SeatRepository;
import com.cg.mtb.repo.TierRepository;
import com.cg.mtb.util.SeatUtil;
 
import jakarta.transaction.Transactional;
 
@Service
public class SeatServiceImpl implements SeatService {
 
    //private final RestTemplate restTemplate;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private TierRepository tierRepository;
    @Autowired
    private RestTemplate restTemplate;
 
//    public SeatServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//        
//    }
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
 
 
    @Override
    public List<SeatDto> getAllSeats() throws SeatNotFoundException {
        List<SeatEntity> seats = seatRepository.findAll();
        if (seats.isEmpty()) {
            throw new SeatNotFoundException("No seats available in the database.");
        }
        return seats.stream().map(SeatUtil::toDto).collect(Collectors.toList());
    }
 
 
	@Override
	public SeatDto getSeatById(int seatId) throws SeatNotFoundException {
		 SeatEntity seat = seatRepository.findById(seatId)
	                .orElseThrow(() -> new SeatNotFoundException("Seat with ID " + seatId + " not found"));
 
	        return SeatUtil.toDto(seat);
	    }
 
 
	@Override
	public List<SeatDto> getSeatsByTheatreId(int theatreId) throws SeatNotFoundException {
		 List<TierEntity> tiers = tierRepository.findByTheatre_TheatreId(theatreId);
	        if (tiers.isEmpty()) {
	            throw new SeatNotFoundException("No tiers found for theatre ID " + theatreId);
	        }
 
	      
	        List<SeatEntity> seats = seatRepository.findByTierIn(tiers);
	        if (seats.isEmpty()) {
	            throw new SeatNotFoundException("No seats found for theatre ID " + theatreId);
	        }
 
	        return seats.stream().map(SeatUtil::toDto).collect(Collectors.toList());
	    }
 
 
	@Override
	public List<SeatDto> getSeatsByTierId(int tierId) throws SeatNotFoundException {
		List<SeatEntity> seats = seatRepository.findByTier_TierId(tierId);
        if (seats.isEmpty()) {
            throw new SeatNotFoundException("No seats found for tier ID " + tierId);
        }
 
        return seats.stream().map(SeatUtil::toDto).collect(Collectors.toList());
    }
 
 
	@Override
	public SeatDto addSeat(SeatDto seatDto) throws TierNotFoundException {
		TierEntity tier = tierRepository.findById(seatDto.getTierId())
                .orElseThrow(() -> new TierNotFoundException("Tier with ID " + seatDto.getTierId() + " not found"));
 
        
        SeatEntity newSeat = new SeatEntity(tier, seatDto.getSeatNumber());
        SeatEntity savedSeat = seatRepository.save(newSeat);
 
        return SeatUtil.toDto(savedSeat);
    }
//	 @Override
//	    @Transactional  // ✅ Ensures changes are committed to DB
//	    public void deleteSeat(int seatId) throws SeatNotFoundException {
//	        if (!seatRepository.existsById(seatId)) {
//	            throw new SeatNotFoundException("Seat with ID " + seatId + " not found");
//	        }
//	        
//	        seatRepository.deleteSeatById(seatId);  // ✅ Calls custom delete query
//	    }
//	}
 
 
	@Override
    @Transactional 
    public void deleteSeat(int seatId) throws SeatNotFoundException {
        if (!seatRepository.existsById(seatId)) {
            throw new SeatNotFoundException("Seat with ID " + seatId + " not found");
        }
        seatRepository.deleteById(seatId);
    }

	
	
}

//	@Override
//    @Transactional
//    public void cancelSeatReservation(int seatId) throws SeatNotFoundException {
//        if (!seatRepository.existsById(seatId)) {
//            throw new SeatNotFoundException("Seat with ID " + seatId + " not found");
//        }
//        
//        int updatedRows = seatRepository.cancelReservation(seatId);
//        if (updatedRows == 0) {
//            throw new SeatNotFoundException("Seat with ID " + seatId + " could not be canceled");
//        }
//    }

 
	

 
 
//	@Override
//	public List<SeatDto> getAvailableSeatsByShowId(int showId) throws SeatNotFoundException {
//		
//		 List<TierEntity> tiers = tierRepository.findByShowId(showId);
//	        if (tiers.isEmpty()) {
//	            throw new SeatNotFoundException("No tiers found for show ID " + showId);
//	        }
//
//	        // ✅ Step 2: Fetch available seats from those tiers
//	        List<SeatEntity> seats = seatRepository.findByTierInAndBooked(tiers, false);
//	        if (seats.isEmpty()) {
//	            throw new SeatNotFoundException("No available seats found for show ID " + showId);
//	        }
//
//	        return seats.stream().map(SeatUtil::toDto).collect(Collectors.toList());
//	    }


