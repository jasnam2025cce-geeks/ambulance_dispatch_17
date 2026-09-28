package com.example.Ambulance_Dispatch.service;

import com.example.Ambulance_Dispatch.entity.Ambulance;
import com.example.Ambulance_Dispatch.entity.Zone;
import com.example.Ambulance_Dispatch.repository.AmbulanceRepository;
import com.example.Ambulance_Dispatch.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmbulanceService {

    private final AmbulanceRepository ambulanceRepository;
    private final ZoneRepository zoneRepository;

    public AmbulanceService(
            AmbulanceRepository ambulanceRepository,
            ZoneRepository zoneRepository) {
        this.ambulanceRepository = ambulanceRepository;
        this.zoneRepository = zoneRepository;
    }

    public Ambulance registerAmbulance(
            String ambulanceNumber,
            Long zoneId) {

        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        Ambulance ambulance = new Ambulance(
                ambulanceNumber,
                zone,
                Ambulance.Status.AVAILABLE
        );

        return ambulanceRepository.save(ambulance);
    }

    public List<Ambulance> getAllAmbulances() {
        return ambulanceRepository.findAll();
    }

    public List<Ambulance> getAvailableAmbulances() {
        return ambulanceRepository.findByStatus(
                Ambulance.Status.AVAILABLE
        );
    }
}