package com.cg.mtb.util;

import com.cg.mtb.dto.TicketBookingDto;
import com.cg.mtb.entity.TicketBookingEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketBookingUtil {

    public static TicketBookingDto mapToDTO(TicketBookingEntity ticket) {
        if (ticket == null) {
            return null;
        }

        TicketBookingDto dto = new TicketBookingDto();
        dto.setTicketId(ticket.getTicketId());
        dto.setShowId(ticket.getShowId());
        dto.setSeatId(ticket.getSeatId());
        dto.setAmount(ticket.getAmount());
        dto.setPurchaseDate(ticket.getPurchaseDate());

        return dto;
    }

    public static TicketBookingEntity mapToEntity(TicketBookingDto dto) {
        if (dto == null) {
            return null;
        }

        TicketBookingEntity ticket = new TicketBookingEntity();
        ticket.setShowId(dto.getShowId());
        ticket.setSeatId(dto.getSeatId());
        ticket.setAmount(dto.getAmount());
        ticket.setPurchaseDate(dto.getPurchaseDate());

        return ticket;
    }

    public static Set<TicketBookingDto> mapToDTOSet(List<TicketBookingEntity> ticketList) {
        if (ticketList == null) {
            return null;
        }
        return ticketList.stream().map(TicketBookingUtil::mapToDTO).collect(Collectors.toSet());
    }

    public static Set<TicketBookingEntity> mapToEntitySet(List<TicketBookingDto> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(TicketBookingUtil::mapToEntity).collect(Collectors.toSet());
    }
}
