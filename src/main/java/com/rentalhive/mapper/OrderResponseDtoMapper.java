package com.rentalhive.mapper;

import com.rentalhive.domain.Order;
import com.rentalhive.dto.response.OrderEquipmentResponseDto;
import com.rentalhive.dto.response.OrderResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderResponseDtoMapper {

    private OrderResponseDtoMapper() {
    }

    public static OrderResponseDto toDto(Order order) {
        List<OrderEquipmentResponseDto> equipmentItemDtos = order.getOrderEquipments().stream()
                .map(OrderEquipmentItemResponseDtoMapper::toDto)
                .collect(Collectors.toList());
        return OrderResponseDto.builder()
                .end(order.getRentEndDate())
                .start(order.getRentStartDate())
                .orderEquipment(equipmentItemDtos)
                .build();
    }

    public static Order toEntity(OrderResponseDto orderDto) {
        return Order.builder()
                .rentStartDate(orderDto.getStart())
                .rentEndDate(orderDto.getEnd())
                .build();
    }
}
