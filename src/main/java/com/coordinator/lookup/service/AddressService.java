package com.coordinator.lookup.service;

import com.coordinator.lookup.dto.AddressResponseDto;
import java.util.List;

public interface AddressService {
    List<AddressResponseDto> getAddressedFromDb();
}
