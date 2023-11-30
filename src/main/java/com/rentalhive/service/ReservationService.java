package com.rentalhive.service;


import com.rentalhive.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
}
