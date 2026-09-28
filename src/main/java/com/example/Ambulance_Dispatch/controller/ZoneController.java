package com.example.Ambulance_Dispatch.controller;

import com.example.Ambulance_Dispatch.entity.Zone;
import com.example.Ambulance_Dispatch.service.ZoneService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public Zone createZone(@Valid @RequestBody Zone zone) {
        return zoneService.createZone(zone);
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable Long id) {
        return zoneService.getZoneById(id);
    }
}