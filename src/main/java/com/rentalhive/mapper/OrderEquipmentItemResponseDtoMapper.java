package com.rentalhive.mapper;

import com.rentalhive.domain.OrderEquipment;
import com.rentalhive.dto.request.OrderEquipmentRequestDto;
import com.rentalhive.dto.response.OrderEquipmentResponseDto;

public class OrderEquipmentItemResponseDtoMapper {

    private OrderEquipmentItemResponseDtoMapper() {
    }

    public static OrderEquipmentResponseDto toDto(OrderEquipment equipmentItem) {
            return OrderEquipmentResponseDto.builder()
                    .id(equipmentItem.getId())
                    .price(equipmentItem.getRentPrice())
                    .equipmentName(equipmentItem.getEquipmentItem().getEquipment().getName())
                    .reference(equipmentItem.getEquipmentItem().getReference())
                    .build();
    }

    public static OrderEquipment toEntity(OrderEquipmentRequestDto orderEquipment) {
        return OrderEquipment.builder()
                .id(orderEquipment.getId())
                .rentPrice(orderEquipment.getPrice())
                .build();
    }
}
