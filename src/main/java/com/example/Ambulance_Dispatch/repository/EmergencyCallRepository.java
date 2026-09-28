package com.example.Ambulance_Dispatch.repository;

import com.example.Ambulance_Dispatch.entity.EmergencyCall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyCallRepository extends JpaRepository<EmergencyCall, Long> {
}
