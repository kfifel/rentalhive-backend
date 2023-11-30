package com.rentalhive.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequestDto {
    @NotBlank(message = "Conditions are required")
    @NotNull(message = "Conditions are required")
    private String conditions;
    @NotNull(message = "Reservation id is required")
    private Long reservationId;
}
