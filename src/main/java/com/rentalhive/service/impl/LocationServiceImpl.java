package com.rentalhive.service.impl;


import com.rentalhive.domain.Location;
import com.rentalhive.repository.LocationRepository;
import com.rentalhive.service.LocationService;
import com.rentalhive.utils.CustomError;
import com.rentalhive.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    @Override
    public Location save(Location location) throws ValidationException {
        Optional<Location> optionalLocationName = locationRepository.findByName(location.getName());
        if(optionalLocationName.isPresent())
            throw new ValidationException(new CustomError("name","Location name already exists"));
        Optional<Location> optionalLocationLatLong = locationRepository.findByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());
        if(optionalLocationLatLong.isPresent())
            throw new ValidationException(new CustomError("latitude, longitude","Location latitude and longitude are already exists"));
        return locationRepository.save(location);
    }

    @Override
    public Location update(Location location) throws ValidationException {
        Optional<Location> optionalLocationName = locationRepository.findByNameAndIdNot(location.getName(), location.getId());
        if(optionalLocationName.isPresent())
            throw new ValidationException(new CustomError("name","Location name already exists"));
        Optional<Location> optionalLocationLatLong = locationRepository.findByLatitudeAndLongitudeAndIdNot(location.getLatitude(), location.getLongitude(), location.getId());
        if(optionalLocationLatLong.isPresent())
            throw new ValidationException(new CustomError("latitude, longitude","Location latitude and longitude are already exists"));
        return locationRepository.save(location);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }
}
