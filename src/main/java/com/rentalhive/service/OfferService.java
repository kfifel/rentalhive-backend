package com.rentalhive.service;

import com.rentalhive.domain.Offer;
import com.rentalhive.enums.OfferStatus;
import com.rentalhive.utils.ValidationException;

import java.util.List;

public interface OfferService {
    List<Offer> getOffersByStatusOrStatus(OfferStatus status, OfferStatus status2);
    Offer createOffer(Offer offer) throws ValidationException;
    Offer acceptOffer(Long id) throws ValidationException;
    Offer rejectOffer(Long id) throws ValidationException;

    Offer negotiatingOffer(Long id) throws ValidationException;
}
