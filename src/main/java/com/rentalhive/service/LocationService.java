package com.rentalhive.service;


import com.rentalhive.domain.Location;
import com.rentalhive.utils.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocationService {
    public Location save(Location location) throws ValidationException;
    public Location update(Location location) throws ValidationException;
    public List<Location> findAll();
    public void delete(Long id);
    public Optional<Location> findById(Long id);
}
