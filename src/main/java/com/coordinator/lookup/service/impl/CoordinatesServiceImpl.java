package com.coordinator.lookup.service.impl;

import com.coordinator.lookup.entity.CoordinatesEntity;
import com.coordinator.lookup.repository.CoordinatesRepository;
import com.coordinator.lookup.service.CoordinatesService;
import com.coordinator.lookup.service.api.CoordinatesApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = {"coordinates"})
@RequiredArgsConstructor
@Service
public class CoordinatesServiceImpl implements CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;
    private final CoordinatesApi coordinatesApi;

    @Override
    public List<CoordinatesEntity> saveCoordinates(List<CoordinatesEntity> coordinatesEntities) {
        return coordinatesRepository.saveAll(coordinatesEntities);
    }

    @Cacheable
    @Override
    public List<CoordinatesEntity> getCoordinatesByAddress(String address) {
        List<CoordinatesEntity> coordinatesByAddress = coordinatesApi
                .getCoordinatesByAddress(address.replaceAll(" ", "%20"));
        return saveCoordinates(coordinatesByAddress);
    }

    @Override
    public List<CoordinatesEntity> getAllCoordinates() {
        return coordinatesRepository.findAll();
    }
}
