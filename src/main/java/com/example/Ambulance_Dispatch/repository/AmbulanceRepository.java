package com.example.Ambulance_Dispatch.repository;

import com.example.Ambulance_Dispatch.entity.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmbulanceRepository extends JpaRepository<Ambulance, Long> {

    List<Ambulance> findByStatus(Ambulance.Status status);
}