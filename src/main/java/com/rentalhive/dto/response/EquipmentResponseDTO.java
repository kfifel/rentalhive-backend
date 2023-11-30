package com.rentalhive.dto.response;

import com.rentalhive.domain.EquipmentFamily;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentResponseDTO {
    private Long id;
    private String name;
    private Long quantityAvailable;
    private EquipmentFamily equipmentFamily;
}
