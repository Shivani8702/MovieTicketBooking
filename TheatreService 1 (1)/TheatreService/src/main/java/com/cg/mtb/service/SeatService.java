package com.cg.mtb.service;
 
import java.util.List;
 
import com.cg.mtb.dto.SeatDto;
import com.cg.mtb.entity.SeatEntity;
import com.cg.mtb.exception.SeatNotFoundException;

import com.cg.mtb.exception.TierNotFoundException;
 
public interface SeatService {

	 List<SeatDto> getAllSeats() throws SeatNotFoundException;

	 SeatDto getSeatById(int seatId) throws SeatNotFoundException;

	// List<SeatDto> getAvailableSeatsByShowId(int showId) throws SeatNotFoundException;

	 List<SeatDto> getSeatsByTheatreId(int theatreId) throws SeatNotFoundException;

	 List<SeatDto> getSeatsByTierId(int tierId) throws SeatNotFoundException;

	 SeatDto addSeat(SeatDto seatDto) throws TierNotFoundException;

	 void deleteSeat(int seatId) throws SeatNotFoundException;



	// void cancelSeatReservation(int seatId) throws SeatNotFoundException;

}

 