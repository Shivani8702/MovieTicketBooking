package com.cg.mtb.util;

import java.util.List;
import java.util.stream.Collectors;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.entity.AddressEntity;

public class AddressUtil {
	 public static AddressDto convertToDTO(AddressEntity address) {
	        if (address == null) {
	            return null;
	        }
	        return new AddressDto(
	            address.getAddressId(),
	            address.getCountry(),
	            address.getState(),
	            address.getCity(),
	            address.getAddressLine()
	        );
	    }
	 public static AddressEntity convertToEntity(AddressDto addressDTO) {
	        if (addressDTO == null) {
	            return null;
	        }
	        AddressEntity address = new AddressEntity();
	        address.setAddressId(addressDTO.getAddressId());
	        address.setCountry(addressDTO.getCountry());
	        address.setState(addressDTO.getState());
	        address.setCity(addressDTO.getCity());
	        address.setAddressLine(addressDTO.getAddressLine());
	        return address;
	    }
	 public static List<AddressDto> convertToDTOList(List<AddressEntity> addresses) {
	        return addresses.stream()
	                .map(AddressUtil::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    public static List<AddressEntity> convertToEntityList(List<AddressDto> addressDTOs) {
	        return addressDTOs.stream()
	                .map(AddressUtil::convertToEntity)
	                .collect(Collectors.toList());
	    }


}
