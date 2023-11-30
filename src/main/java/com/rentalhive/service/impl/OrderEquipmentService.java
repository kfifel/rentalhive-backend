package com.rentalhive.service.impl;

import com.rentalhive.domain.OrderEquipment;
import com.rentalhive.repository.OrderEquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderEquipmentService {

    private final OrderEquipmentRepository orderEquipmentRepository;

    public List<OrderEquipment> saveAll(List<OrderEquipment> orderEquipment) {
        return orderEquipmentRepository.saveAll(orderEquipment);
    }
}
