package com.coordinator.lookup.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatesResponseDto {
    private List<String> lon;
    private List<String> lat;
}
