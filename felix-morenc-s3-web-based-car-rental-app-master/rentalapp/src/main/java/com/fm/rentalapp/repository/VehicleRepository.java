package com.fm.rentalapp.repository;

import com.fm.rentalapp.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Vehicle findByName(String name);
}
