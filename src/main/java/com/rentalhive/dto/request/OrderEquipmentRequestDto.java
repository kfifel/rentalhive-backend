package com.rentalhive.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class OrderEquipmentRequestDto {
    @NotNull(message = "Order equipment id is required")
    private Long id;
    @NotNull(message = "Equipment reservation price is required")
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;
}
