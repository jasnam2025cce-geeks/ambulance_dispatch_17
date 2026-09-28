package com.example.Ambulance_Dispatch.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergency_calls")
public class EmergencyCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caller_zone_id", nullable = false)
    private Zone callerZone;

    @ManyToOne
    @JoinColumn(name = "assigned_ambulance_id")
    private Ambulance assignedAmbulance;

    @Column(nullable = false)
    private LocalDateTime callTime;

    private LocalDateTime responseTime;

    public EmergencyCall() {
    }

    public EmergencyCall(Zone callerZone, LocalDateTime callTime) {
        this.callerZone = callerZone;
        this.callTime = callTime;
    }

    public Long getId() {
        return id;
    }

    public Zone getCallerZone() {
        return callerZone;
    }

    public void setCallerZone(Zone callerZone) {
        this.callerZone = callerZone;
    }

    public Ambulance getAssignedAmbulance() {
        return assignedAmbulance;
    }

    public void setAssignedAmbulance(Ambulance assignedAmbulance) {
        this.assignedAmbulance = assignedAmbulance;
    }

    public LocalDateTime getCallTime() {
        return callTime;
    }

    public void setCallTime(LocalDateTime callTime) {
        this.callTime = callTime;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }
}