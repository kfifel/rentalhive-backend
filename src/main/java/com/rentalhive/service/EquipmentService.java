package com.rentalhive.service;

import com.rentalhive.domain.Equipment;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EquipmentService {
    public Equipment save(Equipment equipment) throws ValidationException;
    public Equipment update(Equipment equipment) throws ValidationException;
    public List<Equipment> findAll();
    public void delete(Long id);
    public Optional<Equipment> findById(Long id);
    public void deleteAll();
}
