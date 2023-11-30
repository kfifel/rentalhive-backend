package com.rentalhive.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentRequestDTO {
    @NotNull(message = "Equipment id is required")
    private Long id;
    private String name;
    @NotNull(message = "Quantity reserved is required")
    private Integer quantityReserved;
}
