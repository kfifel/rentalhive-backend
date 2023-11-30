package com.rentalhive.mapper;

import com.rentalhive.domain.Reservation;
import com.rentalhive.dto.ReservationDto;

public class ReservationDtoMapper {

    public static ReservationDto toDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .offer(OfferDtoMapper.toDto(reservation.getOffer()))
                .build();
    }
}
