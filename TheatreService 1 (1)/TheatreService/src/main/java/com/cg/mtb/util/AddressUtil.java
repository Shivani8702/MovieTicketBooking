package com.cg.mtb.util;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.entity.AddressEntity;

public class AddressUtil {

    public static AddressDto toDto(AddressEntity address) {
        return new AddressDto(
            address.getAddressId(),
            address.getCountry(),
            address.getState(),
            address.getCity(),
            address.getAddressLine()
        );
    }

    public static AddressEntity toEntity(AddressDto dto) {
        AddressEntity address = new AddressEntity();
        address.setAddressId(dto.getAddressId());
        address.setCountry(dto.getCountry());
        address.setState(dto.getState());
        address.setCity(dto.getCity());
        address.setAddressLine(dto.getAddressLine());
        return address;
    }
}
