package com.cg.mtb.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cg.mtb.dto.TicketBookingDto;
import com.cg.mtb.exception.TicketNotAvailableException;
import com.cg.mtb.exception.TicketNotFoundException;
import com.cg.mtb.service.TicketBookingService;

import java.util.Set;

@RestController
@RequestMapping("/api/tickets")
public class TicketBookingController {

    private final TicketBookingService ticketBookingService;

    public TicketBookingController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    // Book a Ticket (User Endpoint)
    @PostMapping("/book")
    public ResponseEntity<TicketBookingDto> bookTicket(@RequestBody TicketBookingDto ticketDTO) throws TicketNotAvailableException {
        TicketBookingDto bookedTicket = ticketBookingService.bookTicket(ticketDTO)
                .orElseThrow(() -> new TicketNotAvailableException("Selected seat is not available."));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookedTicket);
    }

    // View Details of a Booked Ticket (User/Admin)
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketBookingDto> getTicketById(@PathVariable Long ticketId) throws TicketNotFoundException {
        TicketBookingDto ticket = ticketBookingService.getTicketById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));
        return ResponseEntity.ok(ticket);
    }

    // Get All Tickets of a User (User Endpoint)
    @GetMapping
    public ResponseEntity<Set<TicketBookingDto>> getUserTickets() throws TicketNotFoundException {
        Set<TicketBookingDto> tickets = ticketBookingService.getAllUserTickets();
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException("No tickets found for the user.");
        }
        return ResponseEntity.ok(tickets);
    }

    // Admin Endpoints

    // Get All Booked Tickets (Admin)
    @GetMapping("/admin")
    public ResponseEntity<Set<TicketBookingDto>> getAllTickets() throws TicketNotFoundException {
        Set<TicketBookingDto> tickets = ticketBookingService.getAllBookedTickets();
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException("No booked tickets found.");
        }
        return ResponseEntity.ok(tickets);
    }

    // Get Details of a Specific Ticket (Admin)
    @GetMapping("/admin/{ticketId}")
    public ResponseEntity<TicketBookingDto> getAdminTicketById(@PathVariable Long ticketId) throws TicketNotFoundException {
        TicketBookingDto ticket = ticketBookingService.getAdminTicketById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));
        return ResponseEntity.ok(ticket);
    }

    // Cancel a Ticket (Admin)
    @PutMapping("/admin/{ticketId}/cancel")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId) throws TicketNotFoundException {
        TicketBookingDto cancelledTicket = ticketBookingService.cancelTicket(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));
        return ResponseEntity.ok("Ticket with ID " + cancelledTicket.getTicketId() + " has been cancelled successfully.");
    }
}
