package com.rentalhive.dto.response;

import com.rentalhive.enums.EquipmentItemStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class EquipmentItemResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String reference;
    @Enumerated(EnumType.STRING)
    private EquipmentItemStatus status;
}
