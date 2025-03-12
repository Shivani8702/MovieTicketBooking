package com.cg.mtb.ctrl;

import com.cg.mtb.dto.AddressDto;
import com.cg.mtb.exception.AddressNotFoundException;
import com.cg.mtb.exception.TheatreNotFoundException;
import com.cg.mtb.service.AddressService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressCtrl {

    private final AddressService addressService;

    public AddressCtrl(AddressService addressService) {
        this.addressService = addressService;
    }

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }
 //  Get address details by ID
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("addressId") int addressId) throws AddressNotFoundException {
        AddressDto addressDto = addressService.getAddressById(addressId);
        return ResponseEntity.ok(addressDto);
    }
    //Get address for a specific theatre
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<AddressDto> getAddressByTheatreId(@PathVariable("theatreId") int theatreId) throws TheatreNotFoundException {
        AddressDto addressDto = addressService.getAddressByTheatreId(theatreId);
        return ResponseEntity.ok(addressDto);
    }
    //add addresses
    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto) throws AddressNotFoundException {
        AddressDto savedAddress = addressService.addAddress(addressDto);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }
    //delete Addresses
    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer addressId) throws AddressNotFoundException {
        boolean isDeleted = addressService.deleteAddress(addressId);
        if (isDeleted) {
            return ResponseEntity.ok("Address deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //update the address
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable int addressId, @RequestBody AddressDto addressDto) {
        try {
            AddressDto updatedAddress = addressService.updateAddress(addressId, addressDto);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
