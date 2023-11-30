package com.rentalhive.dto.response;

import com.rentalhive.dto.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponseDto {
    private Long id;
    private String conditions;
    private ReservationDto reservation;
}
