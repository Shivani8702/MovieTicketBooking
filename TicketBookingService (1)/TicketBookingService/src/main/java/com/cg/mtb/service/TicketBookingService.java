package com.cg.mtb.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cg.mtb.dto.TicketBookingDto;

public interface TicketBookingService {

    // User Endpoints
	Optional<TicketBookingDto> bookTicket(TicketBookingDto ticketDTO);
    Optional<TicketBookingDto> getTicketById(Long ticketId);
    Set<TicketBookingDto> getAllUserTickets();
  


    // Admin Endpoints
    Set<TicketBookingDto> getAllBookedTickets();
    Optional<TicketBookingDto> getAdminTicketById(Long ticketId);
    Optional<TicketBookingDto> cancelTicket(Long ticketId);
}
