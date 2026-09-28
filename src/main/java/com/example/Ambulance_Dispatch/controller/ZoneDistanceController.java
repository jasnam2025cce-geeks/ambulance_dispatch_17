package com.example.Ambulance_Dispatch.controller;

import com.example.Ambulance_Dispatch.entity.ZoneDistance;
import com.example.Ambulance_Dispatch.service.ZoneDistanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zone-distances")
public class ZoneDistanceController {

    private final ZoneDistanceService zoneDistanceService;

    public ZoneDistanceController(
            ZoneDistanceService zoneDistanceService) {
        this.zoneDistanceService = zoneDistanceService;
    }

    @PostMapping
    public ZoneDistance createDistance(
            @RequestParam Long fromZoneId,
            @RequestParam Long toZoneId,
            @RequestParam Double distance) {

        return zoneDistanceService.createDistance(
                fromZoneId,
                toZoneId,
                distance
        );
    }
}