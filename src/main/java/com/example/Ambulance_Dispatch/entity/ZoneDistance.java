package com.example.Ambulance_Dispatch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(
        name = "zone_distances",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"from_zone_id", "to_zone_id"}
                )
        }
)
public class ZoneDistance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_zone_id", nullable = false)
    private Zone fromZone;

    @ManyToOne
    @JoinColumn(name = "to_zone_id", nullable = false)
    private Zone toZone;

    @Positive(message = "Distance must be positive")
    @Column(nullable = false)
    private Double distance;

    public ZoneDistance() {
    }

    public ZoneDistance(Zone fromZone, Zone toZone, Double distance) {
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public Zone getFromZone() {
        return fromZone;
    }

    public void setFromZone(Zone fromZone) {
        this.fromZone = fromZone;
    }

    public Zone getToZone() {
        return toZone;
    }

    public void setToZone(Zone toZone) {
        this.toZone = toZone;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}