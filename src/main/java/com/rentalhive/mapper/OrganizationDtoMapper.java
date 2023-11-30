package com.rentalhive.mapper;

import com.rentalhive.domain.Organization;
import com.rentalhive.dto.OrganizationDto;

public class OrganizationDtoMapper {

    private OrganizationDtoMapper() {
    }

    public static OrganizationDto toDto(Organization organization){
        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .since(organization.getSince())
                .industry(organization.getIndustry())
                .size(organization.getSize())
                .build();
    }

    public static Organization toEntity(OrganizationDto organizationDto){
        return Organization.builder()
                .name(organizationDto.getName())
                .since(organizationDto.getSince())
                .industry(organizationDto.getIndustry())
                .size(organizationDto.getSize())
                .build();
    }

}
