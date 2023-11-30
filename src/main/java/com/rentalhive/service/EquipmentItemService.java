package com.rentalhive.service;

import com.rentalhive.domain.EquipmentItem;
import com.rentalhive.dto.response.EquipmentResponseDTO;
import com.rentalhive.exception.OrderDateException;

import java.time.LocalDateTime;
import java.util.List;

public interface EquipmentItemService {
    List<EquipmentResponseDTO> findAvailableEquipments(LocalDateTime startDate, LocalDateTime endDate) throws OrderDateException;

    List<EquipmentItem> findAvailableEquipmentItemsByEquipmentId(Long id, LocalDateTime startDate, LocalDateTime endDate);

    List<EquipmentItem> saveAll(List<EquipmentItem> equipmentItems);

    int countEquipmentItemsByEquipmentId(Long id);
}
