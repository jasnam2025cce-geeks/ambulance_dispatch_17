package com.example.Ambulance_Dispatch.controller;

import com.example.Ambulance_Dispatch.entity.Ambulance;
import com.example.Ambulance_Dispatch.entity.EmergencyCall;
import com.example.Ambulance_Dispatch.service.EmergencyCallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency-calls")
public class EmergencyCallController {

    private final EmergencyCallService emergencyCallService;

    public EmergencyCallController(
            EmergencyCallService emergencyCallService) {
        this.emergencyCallService = emergencyCallService;
    }

    @PostMapping
    public EmergencyCall createEmergencyCall(
            @RequestParam Long zoneId) {

        return emergencyCallService.createEmergencyCall(zoneId);
    }

    @GetMapping
    public List<EmergencyCall> getAllCalls() {
        return emergencyCallService.getAllCalls();
    }

    @GetMapping("/available-ambulances")
    public List<Ambulance> getAvailableAmbulances() {
        return emergencyCallService.getAvailableAmbulances();
    }

    @PostMapping("/{id}/allocate")
    public EmergencyCall allocateNearestAmbulance(
            @PathVariable Long id) {

        return emergencyCallService.allocateNearestAmbulance(id);
    }

    @PostMapping("/{id}/complete")
    public EmergencyCall completeEmergencyCall(
            @PathVariable Long id) {

        return emergencyCallService.completeEmergencyCall(id);
    }
}