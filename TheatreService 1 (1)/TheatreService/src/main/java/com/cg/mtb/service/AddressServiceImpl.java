package com.cg.mtb.service;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.entity.AddressEntity;
import com.cg.mtb.entity.TheatreEntity;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.TheatreNotFoundException;
import com.cg.mtb.repo.AddressRepository;
import com.cg.mtb.repo.TheatreRepository;
import com.cg.mtb.util.AddressUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
    private final TheatreRepository theatreRepository; 

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, TheatreRepository theatreRepository) {
        this.addressRepository = addressRepository;
        this.theatreRepository = theatreRepository;
    }
    

    @Override
    public List<AddressDto> getAllAddresses() {
        List<AddressEntity> addressEntities = (List<AddressEntity>) addressRepository.findAll();
        List<AddressDto> addressDtos = new ArrayList<>();

        for (AddressEntity addressEntity : addressEntities) {
            addressDtos.add(AddressUtil.toDto(addressEntity));
        }

        return addressDtos;
    }
    

@Override
public AddressDto getAddressById(int addressId) throws AddressNotFoundException {
    Optional<AddressEntity> optionalAddress = addressRepository.findById(addressId);
    if (optionalAddress.isEmpty()) {
        throw new AddressNotFoundException("Address with ID " + addressId + " not found.");
    }
    return AddressUtil.toDto(optionalAddress.get());
}
@Override
public AddressDto getAddressByTheatreId(int theatreId) throws TheatreNotFoundException {
    Optional<TheatreEntity> theatre = theatreRepository.findById(theatreId);
    if (theatre.isEmpty()) {
        throw new TheatreNotFoundException("Theatre with ID " + theatreId + " not found.");
    }
    return AddressUtil.toDto(theatre.get().getAddress());
}
@Override
public AddressDto addAddress(AddressDto addressDto) {
    AddressEntity addressEntity = AddressUtil.toEntity(addressDto);
    AddressEntity savedAddress = addressRepository.save(addressEntity);
    return AddressUtil.toDto(savedAddress);
}

@Override
public boolean deleteAddress(Integer addressId) {
    if (addressRepository.existsById(addressId)) {
        addressRepository.deleteById(addressId);
        return true;
    }
    return false;
}
@Override
public AddressDto updateAddress(int addressId, AddressDto addressDto) throws AddressNotFoundException {
    Optional<AddressEntity> optionalAddress = addressRepository.findById(addressId);

    if (optionalAddress.isEmpty()) {
        throw new AddressNotFoundException("Address with ID " + addressId + " not found");
    }

    AddressEntity addressEntity = optionalAddress.get();
    addressEntity.setCountry(addressDto.getCountry());
    addressEntity.setState(addressDto.getState());
    addressEntity.setCity(addressDto.getCity());
    addressEntity.setAddressLine(addressDto.getAddressLine());

    AddressEntity updatedEntity = addressRepository.save(addressEntity);
    return AddressUtil.toDto(updatedEntity);
}
}