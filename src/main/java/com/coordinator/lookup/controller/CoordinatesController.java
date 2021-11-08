package com.coordinator.lookup.controller;

import com.coordinator.lookup.entity.CoordinatesEntity;
import com.coordinator.lookup.service.CoordinatesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CoordinatesController {
    private final CoordinatesService coordinatesService;

    @GetMapping("/coordinates")
    List<CoordinatesEntity> getCoordinatesByAddress(@RequestParam String street,
                                                    @RequestParam String houseNumber,
                                                    @RequestParam String city) {
        String address = street + "%20" + houseNumber + "%20" + city;
        return coordinatesService.getCoordinatesByAddress(address);
    }
}
