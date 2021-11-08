package com.coordinator.lookup.service;

import com.coordinator.lookup.entity.CoordinatesEntity;
import java.util.List;

public interface CoordinatesService {
    List<CoordinatesEntity> saveCoordinates(List<CoordinatesEntity> coordinatesEntities);

    List<CoordinatesEntity> getCoordinatesByAddress(String address);

    List<CoordinatesEntity> getAllCoordinates();
}
