//package com.cg.mtb.util;
//
//import com.cg.mtb.dto.TheatreDto;
//import com.cg.mtb.entity.TheatreEntity;
//
//public class TheatreUtil {
//
//    public static TheatreDto toDto(TheatreEntity theatre) {
//        return new TheatreDto(
//            theatre.getTheatreId(),
//            theatre.getAddress().getAddressId(),
//            theatre.getTheatreName(),
//            theatre.getCapacity(),
//            theatre.getScreen()
//        );
//    }
//
//    public static TheatreEntity toEntity(TheatreDto dto) {
//        TheatreEntity theatre = new TheatreEntity();
//        theatre.setTheatreId(dto.getTheatreId());
//        theatre.setTheatreName(dto.getTheatreName());
//        theatre.setCapacity(dto.getCapacity());
//        theatre.setScreen(dto.getScreen());
//        return theatre;
//    }
//}

package com.cg.mtb.util;

import com.cg.mtb.dto.TheatreDto;
import com.cg.mtb.entity.TheatreEntity;

import java.util.ArrayList;
import java.util.List;

public class TheatreUtil {
//Entity To Dto
    public static TheatreDto toDto(TheatreEntity theatre) {
        return new TheatreDto(
            theatre.getTheatreId(),
            theatre.getAddress().getAddressId(),
            theatre.getTheatreName(),
            theatre.getCapacity(),
            theatre.getScreen()
        );
    }
//Dto to Entity
    public static TheatreEntity toEntity(TheatreDto dto) {
        TheatreEntity theatre = new TheatreEntity();
        theatre.setTheatreId(dto.getTheatreId());
        theatre.setTheatreName(dto.getTheatreName());
        theatre.setCapacity(dto.getCapacity());
        theatre.setScreen(dto.getScreen());
        return theatre;
    }

    // Convert List<TheatreEntity> to List<TheatreDto>
    public static List<TheatreDto> toDtoList(List<TheatreEntity> theatreEntities) {
        List<TheatreDto> theatreDtos = new ArrayList<>();
        for (TheatreEntity theatre : theatreEntities) {
            theatreDtos.add(toDto(theatre));
        }
        return theatreDtos;
    }
}

