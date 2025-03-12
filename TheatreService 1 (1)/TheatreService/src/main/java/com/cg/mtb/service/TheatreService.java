package com.cg.mtb.service;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.dto.MovieShowDto;
import com.cg.mtb.dto.TheatreDto;
import com.cg.mtb.entity.TheatreEntity;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.TheatreNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TheatreService {
    List<TheatreDto> getAllTheatres() throws TheatreNotFoundException;
    TheatreDto getTheatreById(int theatreId) throws TheatreNotFoundException;
    AddressDto getTheatreLocation(int theatreId) throws TheatreNotFoundException;
    List<TheatreDto> getTheatresByCity(String cityName)throws TheatreNotFoundException;
    TheatreDto addTheatre(TheatreDto theatreDto)throws AddressNotFoundException;
    void deleteTheatre(int theatreId) throws TheatreNotFoundException;
    TheatreDto updateTheatre(int theatreId, TheatreDto theatreDto) throws TheatreNotFoundException;
    int getTheatreCapacity(int theatreId) throws TheatreNotFoundException;
    List<TheatreDto> getNearbyTheatres(int theatreId) throws TheatreNotFoundException;
    List<TheatreDto> searchTheatreByName(String theatreName) throws TheatreNotFoundException;
    List<TheatreDto> getTheatresByState(String state) throws TheatreNotFoundException;
    String getScreenByTheatreId(int theatreId) throws TheatreNotFoundException;
//    List<MovieShowDto> getMovieShowsByTheatreId(int theatreId) throws TheatreNotFoundException;
    TheatreDto getTheatreByName(String theatreName) throws TheatreNotFoundException;
 



}
