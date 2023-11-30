package com.rentalhive.repository;

import com.rentalhive.domain.EquipmentFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<EquipmentFamily, Long> {
    Optional<EquipmentFamily> findByName(String name);
}
