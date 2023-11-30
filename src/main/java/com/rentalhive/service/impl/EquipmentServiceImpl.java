package com.rentalhive.service.impl;

import com.rentalhive.domain.Equipment;
import com.rentalhive.domain.EquipmentFamily;
import com.rentalhive.domain.EquipmentItem;
import com.rentalhive.enums.EquipmentItemStatus;
import com.rentalhive.repository.EquipmentRepository;
import com.rentalhive.repository.FamilyRepository;
import com.rentalhive.service.EquipmentItemService;
import com.rentalhive.service.EquipmentService;
import com.rentalhive.utils.CustomError;
import com.rentalhive.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final FamilyRepository familyRepository;
    private final EquipmentItemService equipmentItemService;


    @Override
    @Transactional
    public Equipment save(Equipment equipment) throws ValidationException {
        Optional<EquipmentFamily> optionalEquipmentFamily = familyRepository.findById(equipment.getEquipmentFamily().getId());
        if(optionalEquipmentFamily.isEmpty())
            throw new ValidationException(new CustomError("Equipment family","Equipment family does not exist"));
        Optional<Equipment> optionalEquipment = equipmentRepository.findByName(equipment.getName());
        if(optionalEquipment.isPresent())
            throw new ValidationException(new CustomError("name","Equipment name already exists"));
        equipment = equipmentRepository.save(equipment);

        List<EquipmentItem> equipmentItems = createEquipmentItems(equipment.getQuantity(), equipment);
        equipmentItemService.saveAll(equipmentItems);

        return equipment;

    }

    private List<EquipmentItem> createEquipmentItems(int numberOfItems, Equipment equipment){
        List<EquipmentItem> equipmentItems = new ArrayList<>();
        for (int i = 1; i <= numberOfItems; i++) {
            equipmentItems.add(
                    EquipmentItem.builder()
                            .equipment(equipment)
                            .status(EquipmentItemStatus.AVAILABLE)
                            .reference(UUID.randomUUID().toString())
                            .build()
            );
        }
        return equipmentItems;
    }

    @Override
    public Equipment update(Equipment equipment) throws ValidationException {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipment.getId());
        if(equipmentOptional.isEmpty())
            throw new ValidationException( new CustomError("id","Equipment doesn't exist"));
        if(equipment.getQuantity() < equipmentOptional.get().getQuantity())
            throw new RuntimeException("Quantity can't be less than the previous one");
        return equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }

    @Override
    public Optional<Equipment> findById(Long id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        equipmentRepository.deleteAll();
    }
}
