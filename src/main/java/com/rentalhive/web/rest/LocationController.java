package com.rentalhive.web.rest;

import com.rentalhive.domain.Location;
import com.rentalhive.dto.LocationDto;
import com.rentalhive.mapper.LocationDtoMapper;
import com.rentalhive.service.LocationService;
import com.rentalhive.utils.Response;
import com.rentalhive.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<Response<List<LocationDto>>> getAllEquipments(){
        Response<List<LocationDto>> response = new Response<>();
        List<LocationDto> locationList = new ArrayList<>();
        locationService.findAll().stream().map(LocationDtoMapper::toDto).forEach(locationList::add);
        response.setResult(locationList);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<LocationDto>> addLocation(@Valid @RequestBody LocationDto locationDto){
        Response<LocationDto> response = new Response<>();
        Location location = LocationDtoMapper.toEntity(locationDto);
        try {
            response.setResult(LocationDtoMapper.toDto(locationService.save(location)));
            response.setMessage("Location has been added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ValidationException e) {
            response.setMessage("Location has not been added");
            response.setErrors(List.of(e.getCustomError()));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
