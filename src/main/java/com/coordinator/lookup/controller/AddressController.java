package com.coordinator.lookup.controller;

import com.coordinator.lookup.dto.AddressResponseDto;
import com.coordinator.lookup.service.AddressService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/addresses")
    public List<AddressResponseDto> getAllAddresses() {
        return addressService.getAddressedFromDb();
    }
}
