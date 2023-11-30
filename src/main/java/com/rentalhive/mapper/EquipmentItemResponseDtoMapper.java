package com.rentalhive.mapper;

import com.rentalhive.domain.EquipmentItem;
import com.rentalhive.dto.response.EquipmentItemResponseDto;

public class EquipmentItemResponseDtoMapper {

    public static EquipmentItem toEntity(EquipmentItemResponseDto equipmentItemResponseDto) {
        return EquipmentItem.builder()
                .id(equipmentItemResponseDto.getId())
                .status(equipmentItemResponseDto.getStatus())
                .reference(equipmentItemResponseDto.getReference())
                .build();
    }

    public static EquipmentItemResponseDto toDto(EquipmentItem equipmentItem) {
            return EquipmentItemResponseDto.builder()
                    .id(equipmentItem.getId())
                    .status(equipmentItem.getStatus())
                    .reference(equipmentItem.getReference())
                    .build();
    }
}
