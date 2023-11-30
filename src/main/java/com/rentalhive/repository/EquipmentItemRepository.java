package com.rentalhive.repository;

import com.rentalhive.domain.EquipmentItem;
import com.rentalhive.enums.EquipmentItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EquipmentItemRepository extends JpaRepository<EquipmentItem, Long> {

    @Query("SELECT new com.rentalhive.dto.response.EquipmentResponseDTO(" +
            "ei.equipment.id, ei.equipment.name, COUNT(ei.id), ei.equipment.equipmentFamily) " +
            "FROM EquipmentItem ei " +
            "WHERE ei.status = :equipmentItemStatus AND ei.id NOT IN " +
            "(SELECT DISTINCT oe.equipmentItem.id FROM OrderEquipment oe " +
            "WHERE oe.order.rentStartDate < :endDate AND oe.order.rentEndDate > :startDate " +
            "AND oe.order.id IN (SELECT r.offer.order.id FROM Reservation r)) " +
            "GROUP BY ei.equipment.id, ei.equipment.name, ei.equipment.equipmentFamily")
    List<com.rentalhive.dto.response.EquipmentResponseDTO> findAvailableEquipmentResponseDTO(
            EquipmentItemStatus equipmentItemStatus, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT ei FROM EquipmentItem ei " +
            "WHERE ei.equipment.id = :id AND ei.status = :equipmentItemStatus AND ei.id NOT IN " +
            "(SELECT DISTINCT oe.equipmentItem.id FROM OrderEquipment oe " +
            "WHERE oe.order.rentStartDate < :endDate AND oe.order.rentEndDate > :startDate " +
            "AND oe.order.id IN (SELECT r.offer.order.id FROM Reservation r)) ")
    List<EquipmentItem> findAvailableEquipmentItemsByEquipmentId(
            EquipmentItemStatus equipmentItemStatus, Long id, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(ei) FROM EquipmentItem ei WHERE ei.equipment.id = :equipmentId")
    int countByEquipmentId(Long equipmentId);

    @Transactional
    @Modifying
    @Query("update EquipmentItem ei set ei.status = :status where ei.id = :id")
    void updateStatusById(Long id, EquipmentItemStatus status);
}
