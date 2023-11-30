package com.rentalhive.repository;

import com.rentalhive.domain.Offer;
import com.rentalhive.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    public List<Offer> findAllByStatusOrStatus(OfferStatus status, OfferStatus status2);

    Optional<Offer> getByOrderIdAndAndStatus(Long id, OfferStatus status);
}
