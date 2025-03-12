package com.cg.mtb.ctrl;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.UserNotFoundException;
import com.cg.mtb.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addresses")
//http://localhost:8010/addresses
public class AddressCtrl {
    @Autowired
    AddressService addressService;

    // Create a new address for a user
  //http://localhost:8010/addresses/insertAddress/{userId}
    @PostMapping("/insertAddress/{userId}")
    public AddressDto createAddress(@PathVariable int userId, @RequestBody AddressDto addressDTO) {
    	return addressService.createAddress(userId, addressDTO);
    }

    // Get address by user ID
  //http://localhost:8010/addresses/getAddress/{userId}
    @GetMapping("/getAddress/{userId}")
    public AddressDto getAddressByUserId(@PathVariable int userId) throws AddressNotFoundException {
        Optional<AddressDto> address = addressService.getAddressByUserId(userId);
        return address.orElse(null);
    }

    // Update address for a user
  //http://localhost:8010/addresses/updateAddress/{userId}/address/{addressId}
    @PutMapping("/updateAddress/{userId}/address/{addressId}")
    public AddressDto updateAddress(
            @PathVariable int userId,
            @PathVariable int addressId,
            @RequestBody AddressDto addressDTO) throws AddressNotFoundException {
        
        return addressService.updateAddress(userId, addressId, addressDTO);
    }
    // Delete address for a user
  //http://localhost:8010/addresses/deleteaddress/{userId}/address/{addressId}
    @DeleteMapping("/deleteaddress/{userId}/address/{addressId}")
    public void deleteAddress(@PathVariable int userId, @PathVariable int addressId) throws AddressNotFoundException, UserNotFoundException {
        addressService.deleteAddress(userId, addressId);
    }
}
