package com.rentalhive.service;

import com.rentalhive.domain.EquipmentFamily;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FamilyService {
    public EquipmentFamily save(EquipmentFamily equipmentFamily) throws ValidationException;
    public EquipmentFamily update(EquipmentFamily equipmentFamily) throws ValidationException;
    public void delete(Long id);
    public Optional<EquipmentFamily> findById(Long id);
    public List<EquipmentFamily> findAll();

}
