package com.cg.mtb.util;

import com.cg.mtb.dto.SeatDto;
import com.cg.mtb.entity.SeatEntity;

public class SeatUtil {

    public static SeatDto toDto(SeatEntity seat) {
        return new SeatDto(
            seat.getSeatId(),
            seat.getTier().getTierId(),
            seat.getSeatNumber()
        );
    }

    public static SeatEntity toEntity(SeatDto dto) {
        SeatEntity seat = new SeatEntity();
        seat.setSeatId(dto.getSeatId());
        seat.setSeatNumber(dto.getSeatNumber());
        return seat;
    }
}
