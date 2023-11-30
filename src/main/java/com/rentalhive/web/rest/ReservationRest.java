package com.rentalhive.web.rest;

import com.rentalhive.domain.Reservation;
import com.rentalhive.service.ReservationService;
import com.rentalhive.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationRest {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<Response<List<Reservation>>> findAll() {
        return ResponseEntity.ok(Response.<List<Reservation>>builder()
                .message("Reservation found")
                .result(reservationService.findAll())
                .build());
    }

}
