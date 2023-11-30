package com.rentalhive.mapper;

import com.rentalhive.domain.Order;
import com.rentalhive.domain.OrderEquipment;
import com.rentalhive.dto.OrderDto;
import com.rentalhive.dto.response.OrderResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDtoMapper {

    private OrderDtoMapper() {
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .endDate(order.getRentEndDate())
                .startDate(order.getRentStartDate())
                .build();
    }
    public static Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .rentStartDate(orderDto.getStartDate())
                .rentEndDate(orderDto.getEndDate())
                .build();
    }
}
