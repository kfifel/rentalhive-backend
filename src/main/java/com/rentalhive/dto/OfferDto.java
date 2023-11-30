package com.rentalhive.dto;

import com.rentalhive.dto.request.OrderEquipmentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {
    private Long id;

    private Double overallCost;

    private String status;

    @NotNull(message = "Negotiable field is required")
    private Boolean negotiable;

    @NotNull(message = "order is required")
    private Long orderId;

    @NotNull(message = "order equipments are required")
    @Valid
    private List<OrderEquipmentRequestDto> orderEquipments;
}
