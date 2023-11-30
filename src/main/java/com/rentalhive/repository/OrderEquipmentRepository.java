package com.rentalhive.repository;

import com.rentalhive.domain.OrderEquipment;
import com.rentalhive.domain.embedded.OrderEquipmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderEquipmentRepository extends JpaRepository<OrderEquipment, OrderEquipmentId> {
    public List<OrderEquipment> getOrderEquipmentByOrderId(Long orderId);
    @Transactional
    @Modifying
    @Query("UPDATE OrderEquipment oe SET oe.rentPrice = :rentPrice WHERE oe.id = :id")
    public void updateRentPriceById(Long id, Double rentPrice);
}
