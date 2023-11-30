package com.rentalhive.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEquipmentResponseDto {

    private Long id;
    private Double price;
    private String equipmentName;
    private String reference;
}
