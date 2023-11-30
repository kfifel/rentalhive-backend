package com.rentalhive.mapper;

import com.rentalhive.domain.EquipmentFamily;
import com.rentalhive.dto.FamilyDto;

public class FamilyDtoMapper {
    private FamilyDtoMapper(){
    }

    public static FamilyDto toDto(EquipmentFamily family){
        return FamilyDto.builder()
                .id(family.getId())
                .name(family.getName())
                .build();
    }

    public static EquipmentFamily toFamily(FamilyDto familyDto){
        return EquipmentFamily.builder()
                .id(familyDto.getId())
                .name(familyDto.getName())
                .build();
    }
}
