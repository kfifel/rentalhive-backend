//package com.rentalhive.service.impl;
//
//import com.rentalhive.domain.Offer;
//import com.rentalhive.domain.Order;
//import com.rentalhive.enums.OfferStatus;
//import com.rentalhive.repository.OfferRepository;
//import com.rentalhive.repository.OrderRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class OfferServiceTest {
//
//    @InjectMocks
//    private OfferServiceImpl offerService;
//    @Mock
//    private OfferRepository offerRepository;
//    @Mock
//    private OrderRepository orderRepository;
//
//    @Test
//    public void testCreateOffer() {
//        // Arrange
//        Order order = new Order();
//        Double overallCost = 100.0;
//
//        // Mock the behavior of the offerRepository.save method
//        Mockito.when(offerRepository.save(Mockito.any(Offer.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        // Act
//        Offer createdOffer = offerService.createOffer(order, overallCost);
//
//        // Assert
//        assertNotNull(createdOffer);
//        assertEquals(order, createdOffer.getOrder());
//        assertEquals(overallCost, createdOffer.getOverallCost(), 0.001);
//
//        // Verify that the offerRepository.save method was called
//        Mockito.verify(offerRepository, Mockito.times(1)).save(Mockito.any(Offer.class));
//    }
//
//    @Test
//    public void testAcceptOffer() {
//        // Arrange
//        Offer offer = Offer.builder().id(1L).build();
//        when(offerRepository.findById(1L)).thenReturn(Optional.of(offer));
//
//        // Act
//        offerService.acceptOffer(offer);
//
//        // Assert
//        assertEquals(offer.getStatus(), OfferStatus.FULFILLED);
//        assertNotEquals(offer.getStatus(), OfferStatus.NEGOTIATING);
//        assertNotEquals(offer.getStatus(), OfferStatus.REJECTED);
//    }
//
//    @Test
//    public void testRejectOffer() {
//        // Arrange
//        Offer offer = Offer.builder().id(1L).build();
//        when(offerRepository.findById(1L)).thenReturn(Optional.of(offer));
//
//        // Act
//        offerService.rejectOffer(offer);
//
//        assertEquals(offer.getStatus(), OfferStatus.REJECTED);
//        assertNotEquals(offer.getStatus(), OfferStatus.NEGOTIATING);
//        assertNotEquals(offer.getStatus(), OfferStatus.FULFILLED);
//    }
//    @Test
//    public void testNegotiatingOffer() {
//        // Arrange
//        Offer offer = Offer.builder().id(1L).build();
//        when(offerRepository.findById(1L)).thenReturn(Optional.of(offer));
//
//        // Act
//        offerService.negotiatingOffer(offer);
//
//        assertEquals(offer.getStatus(), OfferStatus.NEGOTIATING);
//        assertNotEquals(offer.getStatus(), OfferStatus.REJECTED);
//        assertNotEquals(offer.getStatus(), OfferStatus.FULFILLED);
//    }
//}
