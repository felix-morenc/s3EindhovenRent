package com.fm.rentalapp.service;

import com.fm.rentalapp.entity.Reservation;
import com.fm.rentalapp.entity.Vehicle;
import com.fm.rentalapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public Reservation saveReservation(Reservation reservation)
    {
        return repository.save(reservation);
    }

    public List<Reservation> saveReservations(List<Reservation> reservations)
    {
        return repository.saveAll(reservations);
    }

    public List<Reservation> getReservations()
    {
        return repository.findAll();
    }

    public Reservation getReservationById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    public Reservation getReservationByUserId(Long userId)
    {
        return repository.findByUserId(userId);
    }

    public String deleteReservationById(int id)
    {
        repository.deleteById(id);
        return "Reservation Removed!";
    }

    public Reservation updateReservation(Reservation reservation)
    {
        Reservation existingReservation = repository.findById(reservation.getId()).orElse(null);
        existingReservation.setUserId(reservation.getUserId());
        existingReservation.setVehicleId(reservation.getVehicleId());
        existingReservation.setVehicleName(reservation.getVehicleName());
        existingReservation.setPrice(reservation.getPrice());
        return repository.save(existingReservation);
    }


}