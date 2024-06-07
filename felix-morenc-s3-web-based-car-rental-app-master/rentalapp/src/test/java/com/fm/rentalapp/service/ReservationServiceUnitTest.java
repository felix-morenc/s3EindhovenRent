package com.fm.rentalapp.service;

import com.fm.rentalapp.entity.Reservation;
import com.fm.rentalapp.entity.Vehicle;
import com.fm.rentalapp.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ReservationServiceUnitTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void saveReservation() {
        Reservation reservation = new Reservation();
        reservation.setUserId(Long.valueOf(0));
        reservation.setVehicleId(0);
        reservation.setVehicleName("Audi");
        reservation.setPrice(200);
        Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation savedReservation = reservationService.saveReservation(reservation);
        assertEquals(reservation,savedReservation);

    }

    @Test
    void saveReservations() {
        Reservation reservation = new Reservation();
        reservation.setUserId(Long.valueOf(0));
        reservation.setVehicleId(0);
        reservation.setVehicleName("Audi");
        reservation.setPrice(200);
        Reservation reservation2 = new Reservation();
        reservation2.setUserId(Long.valueOf(1));
        reservation2.setVehicleId(1);
        reservation2.setVehicleName("Volkswagen");
        reservation2.setPrice(200);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        reservations.add(reservation2);
        Mockito.when(reservationRepository.saveAll(reservations)).thenReturn(reservations);
        List<Reservation> savedReservations = reservationService.saveReservations(reservations);
        assertEquals(reservations.get(0).getVehicleId(),savedReservations.get(0).getVehicleId());
        assertEquals(reservations.get(1).getVehicleId(),savedReservations.get(1).getVehicleId());
    }

    @Test
    void getReservations() {
        Reservation reservation = new Reservation();
        reservation.setUserId(Long.valueOf(0));
        reservation.setVehicleId(0);
        reservation.setVehicleName("Audi");
        reservation.setPrice(200);
        Reservation reservation2 = new Reservation();
        reservation2.setUserId(Long.valueOf(1));
        reservation2.setVehicleId(1);
        reservation2.setVehicleName("Volkswagen");
        reservation2.setPrice(200);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        reservations.add(reservation2);
        Mockito.when(reservationRepository.findAll()).thenReturn(reservations);
        List<Reservation> allReservations = reservationService.getReservations();
        assertEquals(reservations,allReservations);
    }

    @Test
    void getReservationById() {
        Reservation reservation = new Reservation();
        reservation.setUserId(Long.valueOf(0));
        reservation.setVehicleId(0);
        reservation.setVehicleName("Audi");
        reservation.setPrice(200);
        Mockito.when(reservationRepository.findById(0)).thenReturn(Optional.of(reservation));

        Reservation foundReservation = reservationService.getReservationById(0);

        assertEquals(0, foundReservation.getId());
    }

    @Test
    void getReservationByUserId() {
        Reservation reservation = new Reservation();
        reservation.setUserId(Long.valueOf(0));
        reservation.setVehicleId(0);
        reservation.setVehicleName("Audi");
        reservation.setPrice(200);
        Mockito.when(reservationRepository.findByUserId(reservation.getUserId())).thenReturn(reservation);
        Reservation foundReservation = reservationService.getReservationByUserId(Long.valueOf(0));
        assertEquals(Long.valueOf(0), foundReservation.getUserId());


    }

    @Test
    void deleteReservationById() {

        Reservation reservation = new Reservation();
        reservation.setUserId(Long.valueOf(0));
        reservation.setVehicleId(0);
        reservation.setVehicleName("Audi");
        reservation.setPrice(200);
        Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation savedReservation = reservationService.saveReservation(reservation);
        int idToDelete = savedReservation.getId();
        String response = reservationService.deleteReservationById(idToDelete);
        assertEquals("Reservation Removed!", response);
    }

    @Test
    void updateReservation() {
        Reservation reservation = new Reservation();
        reservation.setUserId(Long.valueOf(0));
        reservation.setVehicleId(0);
        reservation.setVehicleName("Audi");
        reservation.setPrice(200);
        Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation savedReservation = reservationService.saveReservation(reservation);
        savedReservation.setVehicleName("Toyota");
        Mockito.when(reservationRepository.save(savedReservation)).thenReturn(savedReservation);
        Mockito.when(reservationRepository.findById(savedReservation.getId())).thenReturn(Optional.of(savedReservation));
        Reservation updatedReservation = reservationService.updateReservation(savedReservation);
        assertEquals("Toyota", updatedReservation.getVehicleName());

    }
}