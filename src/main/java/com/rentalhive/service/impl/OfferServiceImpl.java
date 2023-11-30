package com.rentalhive.service.impl;

import com.rentalhive.domain.*;
import com.rentalhive.enums.EquipmentItemStatus;
import com.rentalhive.enums.OfferStatus;
import com.rentalhive.repository.EquipmentItemRepository;
import com.rentalhive.repository.OfferRepository;
import com.rentalhive.repository.OrderEquipmentRepository;
import com.rentalhive.repository.ReservationRepository;
import com.rentalhive.service.OfferService;
import com.rentalhive.utils.CustomError;
import com.rentalhive.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OrderEquipmentRepository orderEquipmentRepository;
    private final EquipmentItemRepository equipmentItemRepository;
    private final ReservationRepository reservationRepository;
    private final User userConnected;

    public Offer getOfferIfExist(Long id) throws ValidationException {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if(offerOptional.isEmpty())
            throw new ValidationException(
                    new CustomError("Offer reference","Offer does not exist")
            );
        return offerOptional.get();
    }
    @Override
    public List<Offer> getOffersByStatusOrStatus(OfferStatus status, OfferStatus status2) {
        return offerRepository.findAllByStatusOrStatus(status, status2);
    }

    @Override
    public Offer createOffer(Offer offer) throws ValidationException {
        if(offerRepository.getByOrderIdAndAndStatus(offer.getOrder().getId(),OfferStatus.PENDING).isPresent())
            throw new ValidationException(
                new CustomError("Order reference","There is already a pending offer for this order")
            );
        List<OrderEquipment> orderEquipments = offer.getOrder().getOrderEquipments();
        List<Long> submittedOrderEquipmentsIds = orderEquipments.stream().map(OrderEquipment::getId).toList();
        List<Long> orderEquipmentsIds = orderEquipmentRepository.getOrderEquipmentByOrderId(offer.getOrder().getId()).stream().map(OrderEquipment::getId).toList();
        Boolean orderEquipmentMatches = new HashSet<>(submittedOrderEquipmentsIds).containsAll(orderEquipmentsIds);
        if(Boolean.FALSE.equals( orderEquipmentMatches))
            throw new ValidationException(
                new CustomError("Order equipment reference","Order equipment does not match")
            );
        orderEquipments.forEach(orderEquipment ->
            orderEquipmentRepository.updateRentPriceById(orderEquipment.getId(), orderEquipment.getRentPrice())
        );
        Double overallCost = orderEquipments.stream().mapToDouble(OrderEquipment::getRentPrice).sum();
        offer.setOverallCost(overallCost);
        return offerRepository.save(offer);
    }

    @Override
    public Offer acceptOffer(Long id) throws ValidationException {
        Offer offer = getOfferIfExist(id);
        offerBelongsToUser(offer);
        if( ! List.of(OfferStatus.PENDING, OfferStatus.NEGOTIATING).contains(offer.getStatus()))
            invalidAction();
        offer.setStatus(OfferStatus.FULFILLED);
        offer.getOrder().getOrderEquipments().forEach(orderEquipment -> {
            Long equipmentId = orderEquipment.getEquipmentItem().getId();
            equipmentItemRepository.updateStatusById(equipmentId, EquipmentItemStatus.BORROWED);
        });
        reservationRepository.save(
                Reservation.builder()
                        .offer(offer)
                        .build()
        );
        return offerRepository.save(offer);
    }

    @Override
    public Offer rejectOffer(Long id) throws ValidationException {
        Offer offer = getOfferIfExist(id);
        offerBelongsToUser(offer);
        if( ! List.of(OfferStatus.PENDING, OfferStatus.NEGOTIATING).contains(offer.getStatus()))
            invalidAction();
        offer.setStatus(OfferStatus.REJECTED);
        return offerRepository.save(offer);
    }

    @Override
    public Offer negotiatingOffer(Long id) throws ValidationException{
        Offer offer = getOfferIfExist(id);
        offerBelongsToUser(offer);
        if( ! OfferStatus.PENDING.equals(offer.getStatus()))
            invalidAction();
        if(Boolean.FALSE.equals( offer.getNegotiable()))
            throw new ValidationException(
                    new CustomError("Offer negotiable","This offer is not negotiable")
            );
        offer.setStatus(OfferStatus.NEGOTIATING);
        return offerRepository.save(offer);
    }

    private void invalidAction() throws ValidationException {
        throw new ValidationException(
                new CustomError("Offer status","Please submit a valid action on this offer")
        );
    }

    private void offerBelongsToUser(Offer offer) throws ValidationException {
        if( ! userConnected.equals(offer.getOrder().getUser()))
            throw new ValidationException(
                new CustomError("Offer reference","This offer does not belong to you")
            );
    }

}
