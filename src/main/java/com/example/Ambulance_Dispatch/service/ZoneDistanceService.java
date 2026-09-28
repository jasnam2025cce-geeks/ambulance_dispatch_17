package com.example.Ambulance_Dispatch.service;

import com.example.Ambulance_Dispatch.entity.Zone;
import com.example.Ambulance_Dispatch.entity.ZoneDistance;
import com.example.Ambulance_Dispatch.repository.ZoneDistanceRepository;
import com.example.Ambulance_Dispatch.repository.ZoneRepository;
import org.springframework.stereotype.Service;

@Service
public class ZoneDistanceService {

    private final ZoneDistanceRepository zoneDistanceRepository;
    private final ZoneRepository zoneRepository;

    public ZoneDistanceService(
            ZoneDistanceRepository zoneDistanceRepository,
            ZoneRepository zoneRepository) {

        this.zoneDistanceRepository = zoneDistanceRepository;
        this.zoneRepository = zoneRepository;
    }

    public ZoneDistance createDistance(
            Long fromZoneId,
            Long toZoneId,
            Double distance) {

        Zone fromZone = zoneRepository.findById(fromZoneId)
                .orElseThrow(() -> new RuntimeException("From zone not found"));

        Zone toZone = zoneRepository.findById(toZoneId)
                .orElseThrow(() -> new RuntimeException("To zone not found"));

        ZoneDistance zoneDistance =
                new ZoneDistance(fromZone, toZone, distance);

        return zoneDistanceRepository.save(zoneDistance);
    }
}