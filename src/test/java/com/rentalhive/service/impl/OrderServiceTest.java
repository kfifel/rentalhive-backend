package com.rentalhive.service.impl;

import com.rentalhive.domain.Equipment;
import com.rentalhive.domain.EquipmentItem;
import com.rentalhive.domain.Location;
import com.rentalhive.dto.OrderDto;
import com.rentalhive.dto.response.EquipmentResponseDTO;
import com.rentalhive.dto.response.OrderResponseDto;
import com.rentalhive.exception.OrderDateException;
import com.rentalhive.exception.QuantityExceededException;
import com.rentalhive.service.EquipmentItemService;
import com.rentalhive.service.EquipmentService;
import com.rentalhive.utils.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EquipmentItemService equipmentItemService;

    @BeforeAll
    void setUp() throws ValidationException {
        System.out.println("initial data for test");

        equipmentService.save(Equipment.builder()
                .name("Tracks")
                .quantity(10)
                .build());
        equipmentService.save(Equipment.builder()
                .name("Ski")
                .quantity(15)
                .build());

    }

    @Test
    void testExistenceOfEquipmentItemsAvailable() throws OrderDateException {

        List<EquipmentResponseDTO> availableEquipments = equipmentItemService.findAvailableEquipments(LocalDateTime.now(), LocalDateTime.now().plusDays(7));

        assertNotNull(availableEquipments);
        assertEquals( 2, availableEquipments.size());

        // asset quantity available of the item with id 1 is 10
        List<EquipmentItem> availableEquipmentItemsByEquipmentId1L = equipmentItemService.
                findAvailableEquipmentItemsByEquipmentId(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        assertEquals(availableEquipmentItemsByEquipmentId1L.size(), 10);

        // asset quantity available of the item with id 2 is 15
        List<EquipmentItem> availableEquipmentItemsByEquipmentId2L = equipmentItemService
                .findAvailableEquipmentItemsByEquipmentId(2L, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        assertEquals(availableEquipmentItemsByEquipmentId2L.size(), 10);
    }

    @Test
    void testCreateOrder() throws Exception, ValidationException {
        // Arrange
        LocalDateTime rentStartDate = LocalDateTime.now();
        LocalDateTime rentEndDate = rentStartDate.plusDays(7);
        createEquipment();

        Location location = Location.builder().name("casa").latitude(24.).longitude(23.).build();

        // TODO: a remplir orderDto
        OrderDto orderDto = OrderDto.builder().build();

        // Act
        OrderResponseDto createdOrder = orderService.createOrder(orderDto);

        // Assert
        assertNotNull(createdOrder);
        assertEquals(rentStartDate, createdOrder.getStart());
        assertEquals(rentEndDate, createdOrder.getEnd());
        // assertEquals(orderEquipments, createdOrder.getEquipments());

        // Verify that the orderRepository.save method was called
    }


    private void createEquipment() throws ValidationException {
        System.out.println("initial data for test");

        equipmentService.save(Equipment.builder()
                .name("Tracks")
                .quantity(10)
                .build());
        equipmentService.save(Equipment.builder()
                .name("Ski")
                .quantity(15)
                .build());

    }
}
