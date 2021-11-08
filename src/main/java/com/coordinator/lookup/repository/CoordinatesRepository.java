package com.coordinator.lookup.repository;

import com.coordinator.lookup.entity.CoordinatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepository extends JpaRepository<CoordinatesEntity, Long> {
}
