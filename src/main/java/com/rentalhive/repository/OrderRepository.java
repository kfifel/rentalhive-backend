package com.rentalhive.repository;

import com.rentalhive.domain.Order;
import com.rentalhive.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT COUNT(o) > 0 " +
            "FROM Offer o " +
            "WHERE o.order.user = :user AND o.status= 'PENDING'")
    boolean isUserHasOrderOfferPending(@Param("user") User user);
}
