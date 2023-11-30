package com.rentalhive.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rentPrice;

    @ManyToOne
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JsonBackReference
    private EquipmentItem equipmentItem;

}
