package com.fm.rentalapp.repository;

import com.fm.rentalapp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findByUserId(Long userId);
}
