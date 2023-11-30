package com.rentalhive.service.impl;

import com.rentalhive.domain.EquipmentFamily;
import com.rentalhive.repository.FamilyRepository;
import com.rentalhive.service.FamilyService;
import com.rentalhive.utils.CustomError;
import com.rentalhive.utils.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;

    @Autowired
    public FamilyServiceImpl(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @Override
    public EquipmentFamily save(EquipmentFamily equipmentFamily) throws ValidationException{
        Optional<EquipmentFamily> optionalEquipmentFamily = familyRepository.findByName(equipmentFamily.getName());
        if(optionalEquipmentFamily.isPresent()){
            throw new ValidationException(new CustomError("name", "Equipment family with this name already exists"));
        }
        return familyRepository.save(equipmentFamily);
    }

    @Override
    public List<EquipmentFamily> findAll() {
        return familyRepository.findAll();
    }

    @Override
    public Optional<EquipmentFamily> findById(Long id) {
        return familyRepository.findById(id);
    }

    @Override
    public EquipmentFamily update(EquipmentFamily equipmentFamily) throws ValidationException {
        Optional<EquipmentFamily> optionalEquipmentFamily = familyRepository.findById(equipmentFamily.getId());
        if (optionalEquipmentFamily.isEmpty()) {
            throw new ValidationException(new CustomError("id", "Equipment family does not exist"));
        }
        return familyRepository.save(equipmentFamily);
    }

    @Override
    public void delete(Long id) {
        familyRepository.deleteById(id);
    }
}
