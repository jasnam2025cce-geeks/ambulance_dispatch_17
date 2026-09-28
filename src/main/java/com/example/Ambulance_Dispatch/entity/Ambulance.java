package com.example.Ambulance_Dispatch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ambulances")
public class Ambulance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ambulance number is required")
    @Column(nullable = false, unique = true)
    private String ambulanceNumber;

    @ManyToOne
    @JoinColumn(name = "home_zone_id", nullable = false)
    private Zone homeZone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        AVAILABLE,
        BUSY
    }

    public Ambulance() {
    }

    public Ambulance(String ambulanceNumber, Zone homeZone, Status status) {
        this.ambulanceNumber = ambulanceNumber;
        this.homeZone = homeZone;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getAmbulanceNumber() {
        return ambulanceNumber;
    }

    public void setAmbulanceNumber(String ambulanceNumber) {
        this.ambulanceNumber = ambulanceNumber;
    }

    public Zone getHomeZone() {
        return homeZone;
    }

    public void setHomeZone(Zone homeZone) {
        this.homeZone = homeZone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}