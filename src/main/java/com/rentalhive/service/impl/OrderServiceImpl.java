package com.rentalhive.service.impl;

import com.rentalhive.domain.*;
import com.rentalhive.dto.OrderDto;
import com.rentalhive.dto.request.EquipmentRequestDTO;
import com.rentalhive.dto.response.OrderResponseDto;
import com.rentalhive.exception.OrderDateException;
import com.rentalhive.exception.QuantityExceededException;
import com.rentalhive.exception.ResourceNotFoundException;
import com.rentalhive.mapper.OrderDtoMapper;
import com.rentalhive.mapper.OrderResponseDtoMapper;
import com.rentalhive.repository.OrderRepository;
import com.rentalhive.service.EquipmentService;
import com.rentalhive.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EquipmentItemServiceImpl equipmentItemService;
    private final User userConnected;
    private final OrderEquipmentService orderEquipmentService;
    private final EquipmentService equipmentService;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderDto orderDto) throws Exception {
        checkIfCanReserveEquipments(orderDto);
        checkIfClientDoesNotHaveOrder();

        List<EquipmentRequestDTO> equipments = orderDto.getEquipments();
        LocalDateTime endDate = orderDto.getEndDate();
        LocalDateTime startDate = orderDto.getStartDate();
        final List<OrderEquipment> orderEquipment = new ArrayList<>();
        final List<EquipmentItem> equipmentItems = new ArrayList<>();

        for (EquipmentRequestDTO equipmentDto:
                equipments) {
            List<EquipmentItem> equipmentItems1 = equipmentItemService.findAvailableEquipmentItemsByEquipmentId(equipmentDto.getId(), startDate, endDate);
            if(equipmentDto.getQuantityReserved() > equipmentItems1.size())
                throw new QuantityExceededException("Quantity reserve more than quantity exist");

            for (int i = 0; i < equipmentDto.getQuantityReserved(); i++) {
                EquipmentItem equipmentItem = equipmentItems1.get(i);
                equipmentItems.add(equipmentItem);
            }
        }

        Order order = OrderDtoMapper.toEntity(orderDto);

        equipmentItems.forEach(equipmentItem ->
                orderEquipment.add(OrderEquipment.builder()
                        .equipmentItem(equipmentItem)
                        .order(order)
                        .build()));

        order.setOrderEquipments(orderEquipment);
        // TODO: get the user authenticated
        order.setUser(userConnected);
        orderRepository.save(order);

        var orderEquipmentsSaved = orderEquipmentService.saveAll(orderEquipment);
        order.setOrderEquipments(orderEquipmentsSaved);
        Order savedOrder = orderRepository.save(order);

        return OrderResponseDtoMapper.toDto(savedOrder);
    }

    private void checkIfClientDoesNotHaveOrder() {
        boolean isUserHasOrderPending =  orderRepository.isUserHasOrderOfferPending(userConnected);

        if(isUserHasOrderPending) {
                throw new IllegalArgumentException("You have already an order pending");
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void checkIfCanReserveEquipments(OrderDto orderDto) throws OrderDateException, ResourceNotFoundException {
        List<EquipmentRequestDTO> equipments = orderDto.getEquipments();
        if(equipments.isEmpty())
            throw new IllegalArgumentException("No equipment is selected");

        validateDate(orderDto.getStartDate(), orderDto.getEndDate());
        checkIfEquipmentsExists(equipments);
    }

    private void checkIfEquipmentsExists(List<EquipmentRequestDTO> equipments) throws ResourceNotFoundException{
        for(EquipmentRequestDTO equipmentRequestDTO: equipments) {
            equipmentService.findById(equipmentRequestDTO.getId()).orElseThrow(() ->
                    new ResourceNotFoundException("EquipmentId", "Equipment with id " + equipmentRequestDTO.getId() + " does not exist"));
        }
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) throws OrderDateException {

        if(startDate.isBefore(LocalDateTime.now()))
            throw new OrderDateException( "Start Date should be after now", "startDate");

        if(endDate.isBefore(startDate))
            throw new OrderDateException("Date start should be before date end", "date");
    }
}
