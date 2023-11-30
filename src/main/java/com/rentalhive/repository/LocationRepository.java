package com.rentalhive.repository;

import com.rentalhive.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Optional<Location> findByName(String name);
    Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
    Optional<Location> findByNameAndIdNot(String name, Long id);
    Optional<Location> findByLatitudeAndLongitudeAndIdNot(Double latitude, Double longitude, Long id);

}
