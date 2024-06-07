package com.fm.rentalapp.controller;

import com.fm.rentalapp.entity.Vehicle;
import com.fm.rentalapp.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VehicleController {

    @Autowired
    private VehicleService service;

    @PostMapping("/addVehicle")
    @PreAuthorize("hasRole('STAFF') or hasRole('OWNER')")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle)
    {
        return service.saveVehicle(vehicle);
    }

    @PostMapping("/addVehicles")
    @PreAuthorize("hasRole('STAFF') or hasRole('OWNER')")
    public List<Vehicle> addVehicles(@RequestBody List<Vehicle> vehicles)
    {
        return service.saveVehicles(vehicles);
    }

    @GetMapping("/vehicles")
    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('OWNER')")
    public List<Vehicle> findAllVehicles()
    {
        return service.getVehicles();
    }

    @GetMapping("/vehicles/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('OWNER')")
    public Vehicle findVehicleById(@PathVariable int id)
    {
        return service.getVehicleById(id);
    }

    @GetMapping("/vehicle/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('OWNER')")
    public Vehicle findVehicleByName(@PathVariable String name)
    {
        return service.getVehicleByName(name);
    }

    @PutMapping("/updateVehicle")
    @PreAuthorize("hasRole('STAFF') or hasRole('OWNER')")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle)
    {
        return service.updateVehicle(vehicle);
    }

    @DeleteMapping("/deleteVehicle/{id}")
    @PreAuthorize("hasRole('STAFF') or hasRole('OWNER')")
    public String deleteVehicle(@PathVariable int id)
    {
        return service.deleteVehicleById(id);
    }




}
