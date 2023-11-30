package com.rentalhive.domain.embedded;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rentalhive.domain.EquipmentItem;
import com.rentalhive.domain.Order;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEquipmentId implements Serializable {

    @ManyToOne
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JsonBackReference
    private EquipmentItem equipmentItem;

}