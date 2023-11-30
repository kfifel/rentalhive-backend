package com.rentalhive.service;

import com.rentalhive.domain.Order;
import com.rentalhive.dto.OrderDto;
import com.rentalhive.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderDto orderRequestDto) throws Exception;
    List<Order> findAll();
}
