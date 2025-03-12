package com.cg.mtb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Service;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.entity.AddressEntity;
import com.cg.mtb.entity.UserEntity;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.UserNotFoundException;
import com.cg.mtb.repo.AddressRepository;
import com.cg.mtb.repo.UserRepository;
import com.cg.mtb.util.AddressUtil;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }


	@Override
	public AddressDto createAddress(int userId, AddressDto addressDTO) {
		UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AddressEntity address = AddressUtil.convertToEntity(addressDTO);
        if (address.getUsers() != null) {
            address.getUsers().add(user);
        } else {
            address.setUsers(List.of(user)); 
        }
        
        AddressEntity savedAddress = addressRepository.save(address);
        return AddressUtil.convertToDTO(savedAddress);
	}

	@Override
	public Optional<AddressDto> getAddressByUserId(int userId) {
		return userRepository.findById(userId)
                .map(UserEntity::getAddress)
                .map(AddressUtil::convertToDTO);
	}

	@Override
	public AddressDto updateAddress(int userId, int addressId, AddressDto addressDTO) throws AddressNotFoundException {
		AddressEntity address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Updating address fields
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setCity(addressDTO.getCity());
        address.setAddressLine(addressDTO.getAddressLine());

        // Ensuring the user is associated with the address
        if (!address.getUsers().contains(user)) {
            address.getUsers().add(user);
        }

        AddressEntity updatedAddress = addressRepository.save(address);
        return AddressUtil.convertToDTO(updatedAddress);
	}

	@Override
	public void deleteAddress(int userId, int addressId) throws AddressNotFoundException, UserNotFoundException {
		AddressEntity address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        address.getUsers().remove(user);
        if (address.getUsers().isEmpty()) {
            addressRepository.delete(address);
        } else {
            addressRepository.save(address);
        }
		
	}


}