package com.rentalhive.mapper;

import com.rentalhive.domain.Offer;
import com.rentalhive.domain.Order;
import com.rentalhive.domain.OrderEquipment;
import com.rentalhive.dto.OfferDto;
import com.rentalhive.enums.OfferStatus;

import java.util.ArrayList;
import java.util.List;

public class OfferDtoMapper {

    private OfferDtoMapper() {
    }

    public static OfferDto toDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .orderId(offer.getOrder().getId())
                .overallCost(offer.getOverallCost())
                .negotiable(offer.getNegotiable())
                .status(offer.getStatus().name())
                .build();
    }

    public static Offer toEntity(OfferDto offerDto) {
        List<OrderEquipment> orderEquipments = new ArrayList<>();
        offerDto.getOrderEquipments().stream()
                .map(OrderEquipmentItemResponseDtoMapper::toEntity)
                .forEach(orderEquipments::add);

        return Offer.builder()
                .negotiable(offerDto.getNegotiable())
                .status(OfferStatus.valueOf(offerDto.getStatus()))
                .order(
                        Order.builder()
                            .id(offerDto.getOrderId())
                            .orderEquipments(orderEquipments)
                            .build()
                )
                .build();
    }
}
