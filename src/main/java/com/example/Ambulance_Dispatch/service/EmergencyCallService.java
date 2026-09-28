package com.example.Ambulance_Dispatch.service;

import com.example.Ambulance_Dispatch.entity.Ambulance;
import com.example.Ambulance_Dispatch.entity.EmergencyCall;
import com.example.Ambulance_Dispatch.entity.Zone;
import com.example.Ambulance_Dispatch.entity.ZoneDistance;
import com.example.Ambulance_Dispatch.repository.AmbulanceRepository;
import com.example.Ambulance_Dispatch.repository.EmergencyCallRepository;
import com.example.Ambulance_Dispatch.repository.ZoneDistanceRepository;
import com.example.Ambulance_Dispatch.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class EmergencyCallService {

    private final ZoneDistanceRepository zoneDistanceRepository;
    private final EmergencyCallRepository emergencyCallRepository;
    private final ZoneRepository zoneRepository;
    private final AmbulanceRepository ambulanceRepository;

    public EmergencyCallService(
            EmergencyCallRepository emergencyCallRepository,
            ZoneRepository zoneRepository,
            AmbulanceRepository ambulanceRepository,
            ZoneDistanceRepository zoneDistanceRepository) {

        this.emergencyCallRepository = emergencyCallRepository;
        this.zoneRepository = zoneRepository;
        this.ambulanceRepository = ambulanceRepository;
        this.zoneDistanceRepository = zoneDistanceRepository;
    }

    // Create a new emergency call
    public EmergencyCall createEmergencyCall(Long zoneId) {

        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() ->
                        new RuntimeException("Zone not found"));

        EmergencyCall call = new EmergencyCall(
                zone,
                LocalDateTime.now()
        );

        return emergencyCallRepository.save(call);
    }

    // Get all emergency calls
    public List<EmergencyCall> getAllCalls() {
        return emergencyCallRepository.findAll();
    }

    // Get all currently available ambulances
    public List<Ambulance> getAvailableAmbulances() {
        return ambulanceRepository.findByStatus(
                Ambulance.Status.AVAILABLE
        );
    }

    // Allocate the nearest available ambulance
    public EmergencyCall allocateNearestAmbulance(Long callId) {

        EmergencyCall call = emergencyCallRepository.findById(callId)
                .orElseThrow(() ->
                        new RuntimeException("Emergency call not found"));

        // Prevent assigning another ambulance
        if (call.getAssignedAmbulance() != null) {
            throw new RuntimeException(
                    "Ambulance already assigned to this call");
        }

        Zone callerZone = call.getCallerZone();

        // Find all available ambulances
        List<Ambulance> availableAmbulances =
                ambulanceRepository.findByStatus(
                        Ambulance.Status.AVAILABLE
                );

        if (availableAmbulances.isEmpty()) {
            throw new RuntimeException(
                    "No available ambulance found");
        }

        // Find the nearest ambulance
        Ambulance nearestAmbulance = availableAmbulances.stream()
                .min(Comparator.comparingDouble(ambulance -> {

                    Zone ambulanceZone = ambulance.getHomeZone();

                    // Same zone = distance 0
                    if (ambulanceZone.getId()
                            .equals(callerZone.getId())) {
                        return 0.0;
                    }

                    // Find predefined distance
                    ZoneDistance zoneDistance =
                            zoneDistanceRepository
                                    .findByFromZoneAndToZone(
                                            callerZone,
                                            ambulanceZone
                                    )
                                    .orElseThrow(() ->
                                            new RuntimeException(
                                                    "Zone distance not defined"
                                            ));

                    return zoneDistance.getDistance();
                }))
                .orElseThrow(() ->
                        new RuntimeException(
                                "No suitable ambulance found"));

        // AVAILABLE → BUSY
        nearestAmbulance.setStatus(
                Ambulance.Status.BUSY
        );

        // Assign ambulance
        call.setAssignedAmbulance(nearestAmbulance);

        // Record response time
        call.setResponseTime(LocalDateTime.now());

        ambulanceRepository.save(nearestAmbulance);

        return emergencyCallRepository.save(call);
    }

    // Complete an emergency call
    public EmergencyCall completeEmergencyCall(Long callId) {

        EmergencyCall call = emergencyCallRepository.findById(callId)
                .orElseThrow(() ->
                        new RuntimeException("Emergency call not found"));

        // Make sure an ambulance was assigned
        if (call.getAssignedAmbulance() == null) {
            throw new RuntimeException(
                    "No ambulance assigned to this call");
        }

        Ambulance ambulance = call.getAssignedAmbulance();

        // BUSY → AVAILABLE
        ambulance.setStatus(
                Ambulance.Status.AVAILABLE
        );

        ambulanceRepository.save(ambulance);

        return emergencyCallRepository.save(call);
    }
}