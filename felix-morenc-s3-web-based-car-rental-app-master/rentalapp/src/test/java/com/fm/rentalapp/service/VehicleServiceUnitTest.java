package com.fm.rentalapp.service;

import com.fm.rentalapp.entity.Vehicle;
import com.fm.rentalapp.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class VehicleServiceUnitTest {

    @MockBean
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void saveVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Audi");
        vehicle.setPrice(200);
        vehicle.setQuantity(4);
        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        assertEquals(vehicle.getName(),savedVehicle.getName());


    }

    @Test
    void saveVehicles() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("Audi");
        vehicle1.setPrice(200);
        vehicle1.setQuantity(4);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("Volkswagen");
        vehicle2.setPrice(400);
        vehicle2.setQuantity(5);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        Mockito.when(vehicleRepository.saveAll(vehicles)).thenReturn(vehicles);
        List<Vehicle> savedVehicles = vehicleService.saveVehicles(vehicles);
        assertEquals(vehicles.get(0).getName(),savedVehicles.get(0).getName());
        assertEquals(vehicles.get(1).getName(),savedVehicles.get(1).getName());

    }

    @Test
    void getVehicles() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setName("Audi");
        vehicle1.setPrice(200);
        vehicle1.setQuantity(4);
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("Volkswagen");
        vehicle2.setPrice(400);
        vehicle2.setQuantity(5);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);
        List<Vehicle> allVehicles = vehicleService.getVehicles();
        assertEquals(vehicles,allVehicles);

    }

    @Test
    void getVehicleById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Audi");
        vehicle.setPrice(200);
        vehicle.setQuantity(4);
        Mockito.when(vehicleRepository.findById(0)).thenReturn(Optional.of(vehicle));

        Vehicle foundVehicle = vehicleService.getVehicleById(0);

        assertEquals(0, foundVehicle.getId());


    }

    @Test
    void getVehicleByName() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Audi");
        vehicle.setPrice(200);
        vehicle.setQuantity(4);
        Mockito.when(vehicleRepository.findByName("Audi")).thenReturn(vehicle);

        Vehicle foundVehicle = vehicleService.getVehicleByName("Audi");

        assertEquals("Audi", foundVehicle.getName());
    }

    @Test
    void deleteVehicleById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Audi");
        vehicle.setPrice(200);
        vehicle.setQuantity(4);
        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        int idToDelete = savedVehicle.getId();
        String response = vehicleService.deleteVehicleById(idToDelete);
        assertEquals("Vehicle Removed!", response);


    }

    @Test
    void updateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Audi");
        vehicle.setPrice(200);
        vehicle.setQuantity(4);
        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        savedVehicle.setName("Toyota");
        Mockito.when(vehicleRepository.save(savedVehicle)).thenReturn(savedVehicle);
        Mockito.when(vehicleRepository.findById(savedVehicle.getId())).thenReturn(Optional.of(savedVehicle));
        Vehicle updatedVehicle = vehicleService.updateVehicle(savedVehicle);
        assertEquals("Toyota", updatedVehicle.getName());

    }
}