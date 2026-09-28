package com.example.Ambulance_Dispatch.repository;

import com.example.Ambulance_Dispatch.entity.Zone;
import com.example.Ambulance_Dispatch.entity.ZoneDistance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZoneDistanceRepository extends JpaRepository<ZoneDistance, Long> {

    Optional<ZoneDistance> findByFromZoneAndToZone(
            Zone fromZone,
            Zone toZone
    );
}