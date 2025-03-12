package com.cg.mtb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.mtb.dto.TicketBookingDto;
import com.cg.mtb.entity.TicketBookingEntity;
import com.cg.mtb.repository.TicketBookingRepository;
import com.cg.mtb.util.TicketBookingUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {

    @Autowired
    private TicketBookingRepository ticketRepository;

    @Override
    @Transactional
    public Optional<TicketBookingDto> bookTicket(TicketBookingDto ticketDTO) {
        TicketBookingEntity ticket = TicketBookingUtil.mapToEntity(ticketDTO);
        ticket.setPurchaseDate(LocalDate.now());
        TicketBookingEntity savedTicket = ticketRepository.save(ticket);
        return Optional.ofNullable(TicketBookingUtil.mapToDTO(savedTicket));
    }

    @Override
    public Optional<TicketBookingDto> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).map(TicketBookingUtil::mapToDTO);
    }

    @Override
    public Set<TicketBookingDto> getAllUserTickets() {
        List<TicketBookingEntity> ticketList = ticketRepository.findAll();
        return ticketList.stream().map(TicketBookingUtil::mapToDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<TicketBookingDto> getAllBookedTickets() {
        List<TicketBookingEntity> ticketList = ticketRepository.findAll();
        return ticketList.stream().map(TicketBookingUtil::mapToDTO).collect(Collectors.toSet());
    }

    @Override
    public Optional<TicketBookingDto> getAdminTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).map(TicketBookingUtil::mapToDTO);
    }

    @Override
    @Transactional
    public Optional<TicketBookingDto> cancelTicket(Long ticketId) {
        Optional<TicketBookingEntity> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            TicketBookingEntity ticket = optionalTicket.get();
            ticketRepository.delete(ticket);
            return Optional.of(TicketBookingUtil.mapToDTO(ticket));
        }
        return Optional.empty();
    }
}
