package com.example.Ambulance_Dispatch.repository;

import com.example.Ambulance_Dispatch.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
}