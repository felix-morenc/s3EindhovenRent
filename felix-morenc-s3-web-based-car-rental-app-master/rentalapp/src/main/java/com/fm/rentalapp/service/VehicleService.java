package com.fm.rentalapp.service;

import com.fm.rentalapp.entity.Vehicle;
import com.fm.rentalapp.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public Vehicle saveVehicle(Vehicle vehicle)
    {
        return repository.save(vehicle);
    }

    public List<Vehicle> saveVehicles(List<Vehicle> vehicles)
    {
        return repository.saveAll(vehicles);
    }

    public List<Vehicle> getVehicles()
    {
        return repository.findAll();
    }

    public Vehicle getVehicleById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public Vehicle getVehicleByName(String name)
    {
        return repository.findByName(name);
    }

    public String deleteVehicleById(int id)
    {
        repository.deleteById(id);
        return "Vehicle Removed!";
    }

    public Vehicle updateVehicle(Vehicle vehicle)
    {
        Vehicle existingVehicle = repository.findById(vehicle.getId()).orElse(null);
        existingVehicle.setName(vehicle.getName());
        existingVehicle.setQuantity(vehicle.getQuantity());
        existingVehicle.setPrice(vehicle.getPrice());
        return repository.save(existingVehicle);
    }


}
