package com.example.Ambulance_Dispatch.controller;

import com.example.Ambulance_Dispatch.entity.Ambulance;
import com.example.Ambulance_Dispatch.service.AmbulanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ambulances")
public class AmbulanceController {

    private final AmbulanceService ambulanceService;

    public AmbulanceController(AmbulanceService ambulanceService) {
        this.ambulanceService = ambulanceService;
    }

    @PostMapping
    public Ambulance registerAmbulance(
            @RequestParam String ambulanceNumber,
            @RequestParam Long zoneId) {

        return ambulanceService.registerAmbulance(
                ambulanceNumber,
                zoneId
        );
    }

    @GetMapping
    public List<Ambulance> getAllAmbulances() {
        return ambulanceService.getAllAmbulances();
    }

    @GetMapping("/available")
    public List<Ambulance> getAvailableAmbulances() {
        return ambulanceService.getAvailableAmbulances();
    }
}