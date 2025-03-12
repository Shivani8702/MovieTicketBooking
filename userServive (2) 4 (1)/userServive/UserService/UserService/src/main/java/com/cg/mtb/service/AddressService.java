package com.cg.mtb.service;

import java.util.Optional;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.dto.UserDto;
import com.cg.mtb.entity.UserEntity;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.UserNotFoundException;

public interface AddressService {

	AddressDto createAddress(int userId, AddressDto addressDTO);
    Optional<AddressDto> getAddressByUserId(int userId) throws AddressNotFoundException;
    AddressDto updateAddress(int userId, int addressId, AddressDto addressDTO)  throws AddressNotFoundException;
    void deleteAddress(int userId, int addressId) throws AddressNotFoundException, UserNotFoundException;
}