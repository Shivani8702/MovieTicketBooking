package com.cg.mtb.service;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.TheatreNotFoundException;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAllAddresses();
    AddressDto getAddressById(int addressId) throws AddressNotFoundException;
    AddressDto getAddressByTheatreId(int theatreId) throws TheatreNotFoundException;
    AddressDto addAddress(AddressDto addressDto)throws AddressNotFoundException;
    boolean deleteAddress(Integer addressId)throws AddressNotFoundException;
    AddressDto updateAddress(int addressId, AddressDto addressDto)throws AddressNotFoundException;
}
