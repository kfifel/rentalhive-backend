package com.rentalhive.mapper;


import com.rentalhive.domain.Location;
import com.rentalhive.dto.LocationDto;

public class LocationDtoMapper {
    private LocationDtoMapper(){
    }

    public static LocationDto toDto(Location location){
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }


    public static Location toEntity(LocationDto locationDto){

        return Location.builder()
                .id(locationDto.getId())
                .name(locationDto.getName())
                .latitude(locationDto.getLatitude())
                .longitude(locationDto.getLongitude())
                .build();
    }
}
