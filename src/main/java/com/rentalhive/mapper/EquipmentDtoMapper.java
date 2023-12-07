package com.rentalhive.mapper;

import com.rentalhive.domain.Equipment;
import com.rentalhive.dto.EquipmentDto;

public class EquipmentDtoMapper {

    private EquipmentDtoMapper(){
    }

    public static EquipmentDto toDto(Equipment equipment){
        return EquipmentDto.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .quantity(equipment.getQuantity())
                .family(FamilyDtoMapper.toDto(equipment.getEquipmentFamily()))
                .build();
    }


    public static Equipment toEquipment(EquipmentDto equipmentDto){

        return Equipment.builder()
                .id(equipmentDto.getId())
                .name(equipmentDto.getName())
                .quantity(equipmentDto.getQuantity())
                .equipmentFamily(FamilyDtoMapper.toFamily(equipmentDto.getFamily()))
                .build();
    }
}
