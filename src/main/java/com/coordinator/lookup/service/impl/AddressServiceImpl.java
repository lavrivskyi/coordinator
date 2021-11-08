package com.coordinator.lookup.service.impl;

import com.coordinator.lookup.dto.AddressResponseDto;
import com.coordinator.lookup.entity.CoordinatesEntity;
import com.coordinator.lookup.service.AddressService;
import com.coordinator.lookup.service.CoordinatesService;
import com.coordinator.lookup.service.api.AddressApi;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = {"addresses"})
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressApi addressApi;
    private final CoordinatesService coordinatesService;

    @Cacheable
    @Override
    public List<AddressResponseDto> getAddressedFromDb() {
        List<CoordinatesEntity> allCoordinates = coordinatesService.getAllCoordinates();
        return allCoordinates.stream()
                .map(coordinatesEntity -> addressApi.requestAddress(coordinatesEntity.getLatitude(),
                        coordinatesEntity.getLongitude()))
                .collect(Collectors.toList());
    }
}
