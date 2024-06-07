package com.fm.rentalapp.controller;

import com.fm.rentalapp.entity.Reservation;
import com.fm.rentalapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("/addReservation")
    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('OWNER')")
    public Reservation addReservation(@RequestBody Reservation reservation) {return service.saveReservation(reservation);}

    @PostMapping("/addReservations")
    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('OWNER')")
    public List<Reservation> addReservations(@RequestBody List<Reservation> reservations) {return service.saveReservations(reservations); }

    @GetMapping("/reservations")
    @PreAuthorize("hasRole('STAFF') or hasRole('OWNER')")
    public List<Reservation> findAllReservations()
    {
        return service.getReservations();
    }

    @GetMapping("/reservation/{id}")
    @PreAuthorize("hasRole('STAFF') or hasRole('OWNER')")
    public Reservation findReservationById(@PathVariable int id)
    {
        return service.getReservationById(id);
    }

    @PutMapping("/updateReservation")
    public Reservation updateReservation(@RequestBody Reservation reservation) {return service.updateReservation(reservation); }

    @DeleteMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable int id)
    {return service.deleteReservationById(id);}




}
